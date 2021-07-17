package com.example.train.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.train.pojo.document.Document;
import com.example.train.pojo.program.*;
import com.example.train.service.*;
import com.example.train.utils.FileUtil;
import com.example.train.utils.JSONUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.relational.core.sql.In;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping(value = "/pros")
@RestController
public class ProgramController {

    @Value("${proPath}")
    private String prefix ;

    @Autowired
    private ProgramService programService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResDocService resDocService;

    @Autowired
    private ProResService proResService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private ProEmpService proEmpService;

    /**
     * 展示培训计划
     * @return
     */
    @RequestMapping(value = "/shows")
    public String retPrograms(){
        List<Program> programs = programService.findAll();
        return JSONUtil.JSONSuccessToString(programs);
    }

    /**
     * 根据名称，类型，发布时间，状态查询
     * @param programDes
     * @return
     */
    @RequestMapping(value ="/show")
    public String retProgram(@RequestBody ProgramDes programDes){
        List<Program> programs = programService.findByProgramDes(programDes);
        return JSONUtil.JSONSuccessToString(programs);
    }

    /**
     * 发布培训计划
     * @param id
     * @return
     */
    @RequestMapping(value = "/publish")
    public String publish(@Param("id")Integer id){
        boolean cancel = programService.modifyStatus(id,"发布");
        if (cancel)
            return JSONUtil.JSONSuccessToString("修改成功");
        else
            return JSONUtil.JSONFailedToString("修改失败");
    }

    /**
     * 修改培训计划为取消发布
     * @param id
     * @return
     */
    @RequestMapping(value = "/cancelStatus")
    public String cancelPub(@Param("id")Integer id){
        boolean cancel = programService.modifyStatus(id,"取消发布");
        if (cancel)
            return JSONUtil.JSONSuccessToString("修改成功");
        else
            return JSONUtil.JSONFailedToString("修改失败");
    }

    /**
     * 根据id删除培训计划
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    public String deleteProgram(@Param("id")Integer id){
        boolean delete = programService.deleteProgram(id);
        if (delete)
            return JSONUtil.JSONSuccessToString("删除成功");
        else
            return JSONUtil.JSONFailedToString("删除失败");
    }

    /**
     * 新增培训时操作数据库获取id
     * @return
     */
    @RequestMapping(value = "getId")
    public String getProgramId(){
        Program program = new Program();
        program.setStatus("未发布");
        programService.addProgram(program);
        Map<String,Integer> map = new HashMap<>();
        map.put("id",program.getId());
        return JSONUtil.JSONSuccessToString(map);
    }

    /**
     * 添加培训
     * @param program
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addProgram(@RequestBody Program program){
//        System.out.println(program);
        Date date = new Date(System.currentTimeMillis());
        program.setPublishTime(date);
        boolean add = programService.modifyProgram(program);
        if (add)
            return JSONUtil.JSONSuccessToString("添加成功");
        else
            return JSONUtil.JSONFailedToString("添加失败");
    }

    /**
     * 培训照片的上传操作
     * @param file
     * @param id
     */
    @RequestMapping(value = "/loadPhoto")
    public void loadPgrogramPhoto(@RequestParam("file")MultipartFile file,
                                  @RequestParam("id")Integer id){
//        System.out.println(file);
        String path = file.getOriginalFilename();
        programService.modifyProgramPath(id, path);
//        System.out.println(programService.modifyProgramPath(id, path));
        FileUtil.uploadDocument(prefix,file);
    }

    /**
     * 获取资源id值
     * @return
     */
    @RequestMapping(value = "/getResId")
    public String getResourceId(){
        Map<String,Integer> map = new HashMap<>();
        Resource resource = new Resource();
        boolean add = resourceService.addResource(resource);
        map.put("id",resource.getId());
        return JSONUtil.JSONSuccessToString(map);
    }

    /**
     * 删除资源与培训关联
     * @param id
     * @return
     */
    @RequestMapping(value = "/delResId")
    public String deleteResourceById(@RequestParam("id")Integer id){

        String path = resourceService.getPathById(id);
        if (path != "" && path != null)
            FileUtil.deleteFile(prefix+"resources/"+path);
        boolean delete = resourceService.deleteResource(id);
//        System.out.println(path);
        boolean deleteResDocs = resDocService.deleteResDoc(id);
        if (delete)
            return JSONUtil.JSONSuccessToString("删除成功");
        else
            return JSONUtil.JSONFailedToString("删除失败");
    }

    /**
     * 处理培训资料在培训取消时对于培训资料的处理和封面照片、绑定文件的处理
     * @param resIds
     * @return
     */
    @RequestMapping(value = "/delResIds",method = RequestMethod.POST)
    public String deleteResourceIds(@RequestBody List<Integer> resIds){
        System.out.println("处理资源");
        for (Integer resId : resIds) {
            String resPath = resourceService.getPathById(resId);
            System.out.println(resPath+" 资源封面的图片地址信息");
            if (resPath != null && resPath != "")
                FileUtil.deleteFile(prefix+"resources/"+resPath);
            System.out.println("删除资源");
            resourceService.deleteResource(resId);
            System.out.println("删除资源与绑定文件的关系");
            resDocService.deleteResDoc(resId);
        }
        return JSONUtil.JSONSuccessToString("删除成功");
    }

    /**
     * 上传资源封面图片以及修改资源路径问题
     * @param file
     * @param id
     * @return
     */
    @RequestMapping(value = "/uploadResPhoto")
    public String uploadResourcePhoto(@RequestParam("file")MultipartFile file,
                                    @RequestParam("id") Integer id){
        String path = file.getOriginalFilename();
        boolean uploadResPhoto = resourceService.modifyPathById(id,path);
//        System.out.println(uploadResPhoto);
        if (uploadResPhoto){
            FileUtil.uploadDocument(prefix+"resources/",file);
            return JSONUtil.JSONSuccessToString("资源封面上传成功");
        }else
            return JSONUtil.JSONFailedToString("资源封面上传失败");
    }

    /**
     * 添加培训资料信息
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/addRes",method = RequestMethod.POST)
    public String addResource(@RequestBody JSONObject jsonObject){
//        System.out.println(jsonObject);

        Integer proId = jsonObject.getInteger("proId");
        JSONArray jsonArray = jsonObject.getJSONArray("res");
        List<Resource> list = JSONObject.parseArray(jsonArray.toJSONString(), Resource.class);
        for (Resource resource : list) {
            Integer resId = resource.getId();
            proResService.addProRes(proId,resId);
            resourceService.modifyResource(resource);
//            System.out.println(resource);
        }
        return JSONUtil.JSONSuccessToString("添加成功");
    }

    /**
     * 处理前端资源与绑定文件的关系
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/resDocs",method = RequestMethod.POST)
    public String addResDocs(@RequestBody JSONObject jsonObject){
//        System.out.println(jsonObject);
        JSONArray jsonArray = jsonObject.getJSONArray("docIds");
        Integer resId = jsonObject.getInteger("resId");
        for (Object o : jsonArray) {
            Integer docId = (Integer)o;
//            System.out.println(docId);
            resDocService.addResDoc(resId, docId);
//            System.out.println(resDocService.addResDoc(resId, docId));
        }
//        System.out.println(resId);
        return JSONUtil.JSONSuccessToString("资源与文件关联成功");
    }

    /**
     * 根据id得到复训的所有信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/retrain")
    public String getProgramAndResourceAndDocument(@RequestParam("id") Integer id){
        ProgramDetail programDetail = new ProgramDetail();

        Program program = programService.findById(id);
        programDetail.setProgram(program);

        List<ResourceDetail> resourceDetails = new ArrayList<>();
        List<Integer> resIds = proResService.getResIdsByProId(id);
        if (resIds != null){
            for (Integer resId : resIds) {
//                System.out.println(resId);
                ResourceDetail resourceDetail = new ResourceDetail();

                Resource resource = resourceService.getByResId(resId);
//                System.out.println(resource);
                resourceDetail.setResource(resource);

                List<String> docNames = new ArrayList<>();
                List<Integer> docIds = resDocService.findDocIdsByResId(resId);
                if (docIds != null){
                    for (Integer docId : docIds) {
//                        System.out.println(docId);
                        String name = documentService.findNameById(docId);
//                        System.out.println(name);
                        docNames.add(name);
                    }
                }
                resourceDetail.setDocNames(docNames);
                resourceDetails.add(resourceDetail);
            }
        }
        programDetail.setResourceDetails(resourceDetails);
        System.out.println(programDetail);
        return JSONUtil.JSONSuccessToString(programDetail);
    }

    @RequestMapping(value = "/getDocs")
    public String getDocsByResId(@RequestParam("resId") Integer resId){
        System.out.println(resId);
        List<Integer> docIds = resDocService.findDocIdsByResId(resId);
        List<Document> documents = new ArrayList<>();
        if (docIds != null){
            for (Integer docId : docIds) {
                Document document = documentService.findById(docId);
                documents.add(document);
            }
        }
        return JSONUtil.JSONSuccessToString(documents);
    }

    /**
     * 添加培训人员
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "addEmp",method = RequestMethod.POST)
    public String programAddEmployees(@RequestBody JSONObject jsonObject){
        JSONArray jsonArray = jsonObject.getJSONArray("emps");
        Integer proId = jsonObject.getInteger("proId");
        boolean addEmps = true;
        for (Object o : jsonArray) {
            addEmps = addEmps && proEmpService.programAddEmployees(proId,(Integer)o);
        }
        System.out.println(addEmps);
        if (addEmps)
            return JSONUtil.JSONSuccessToString("添加组织成功");
        else
            return JSONUtil.JSONFailedToString("添加组织失败");
    }


}
