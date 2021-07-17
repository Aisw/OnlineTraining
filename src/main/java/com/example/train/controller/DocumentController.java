package com.example.train.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.train.pojo.document.DocBook;
import com.example.train.pojo.document.Document;
import com.example.train.service.DocumentService;
import com.example.train.utils.FileUtil;
import com.example.train.utils.JSONUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * 对于文件的处理控制层
 */
@RestController
@RequestMapping(value = "/doc")
public class DocumentController {

    @Value("${videoPath}")
    String prefix ;
//    = "D:/OnlineTraining/src/main/resources/files/video/"

    @Autowired
    private DocumentService documentService;

    /**
     * 获取文件的列表
     * @return
     */
    @RequestMapping(value = "/shows")
    public String retDocuments(){
        List<Document> documents = documentService.findAll();
        return JSONUtil.JSONSuccessToString(documents);
    }

    /**
     * 根据名字和发布时间模糊查询
     * @param document
     * @return
     */
    @RequestMapping(value = "/show")
    public String showDoc(@RequestBody Document document){
        System.out.println(document.getName() + document.getTime());
        List<Document> documents = new ArrayList<>();
        documents = documentService.findByNameAndTime(document.getName(),document.getTime());
        return JSONUtil.JSONSuccessToString(documents);
    }

    /**
     * 添加电子书
     * @param file
     * @param name
     * @return
     */
    @RequestMapping(value = "/upload")
    public String addBook(@RequestParam("file") MultipartFile file,
                          @RequestParam("name") String name,
                          @RequestParam("type") String type,
                          @RequestParam("permit") String permit,
                          @RequestParam("time") Double duration,
                          @RequestParam("books") String booksId){

        List ids =  JSONObject.parseArray(booksId);
        Date date = new Date(System.currentTimeMillis());
        String path = file.getOriginalFilename();

        Document document = new Document();
        document.setName(name);
        document.setTime(date);
        document.setPath(path);
        document.setType(type);
        document.setDuration(duration);
        document.setIsPermit(permit);

        System.out.println(permit+"-----");
        System.out.println(document);

        boolean addDocument = documentService.addDocument(document);

        //处理文件与电子书关联关系
        Integer id = document.getId();
        for (Object o : ids) {
            DocBook docBook = new DocBook();
            docBook.setDocId(id);
            docBook.setBookId(Integer.parseInt(String.valueOf(o)));
            documentService.addDocBook(docBook);
        }

        //将文件保存到本地
        String message = FileUtil.uploadDocument(prefix,file);

        if (addDocument)
            return JSONUtil.JSONSuccessToString(message);
        else
            return JSONUtil.JSONFailedToString("上传错误");
    }

    /**
     * 根据id删除文件
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    public String deleteDock(@Param("id") Integer id,
                             @Param("path") String path){

        FileUtil.deleteFile(prefix+path);
        System.out.println(prefix + path);
        boolean deleteDoc = documentService.deleteDocumentById(id);
        if (deleteDoc)
            return JSONUtil.JSONSuccessToString("删除成功");
        else
            return JSONUtil.JSONFailedToString("删除失败");
    }

    /**
     * 上传修改电子书
     * @param file
     * @param id
     * @param name
     * @return
     */
    @RequestMapping(value = "/update")
    public String retUpdate(@RequestParam("file")MultipartFile file,
                            @RequestParam("id")Integer id,
                            @RequestParam("name")String name){

        //修改附件时先删除之前上传文件
        String deletePath = documentService.getPathById(id);
        FileUtil.deleteFile(prefix+deletePath);

        Date nowDate = new Date(System.currentTimeMillis());
        String path = file.getOriginalFilename();

        Document document = new Document();
        document.setId(id);
        document.setName(name);
        document.setTime(nowDate);
        document.setPath(path);
        documentService.modifyDocument(document);

        //附件写入
        String message = FileUtil.uploadDocument(prefix,file);

        return JSONUtil.JSONSuccessToString(message);
    }

    /**
     * 根据路径下载文件
     * @param id
     * @param response
     */
    @CrossOrigin
    @RequestMapping(value = "/load")
    public void load(@Param("id")Integer id, HttpServletResponse response){

        Document document = documentService.findById(id);
        System.out.println(document+"下载");
        boolean isPermit = Boolean.parseBoolean(document.getIsPermit());
        String path = document.getPath();
        if (isPermit)
            FileUtil.download(prefix+path,response);
    }
}
