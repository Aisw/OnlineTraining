package com.example.train.controller;

import com.example.train.pojo.depart.Depart;
import com.example.train.pojo.depart.DepartDes;
import com.example.train.pojo.depart.Department;
import com.example.train.service.DepartmentDesService;
import com.example.train.service.DepartmentService;
import com.example.train.utils.JSONUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/dep")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentDesService departmentDesService;

    /**
     * 获取organization的所有组织及其所有下级的节点
     * @return
     */
    @RequestMapping(value = "/shows")
    public String returnString(){
        List<Department> departments = departmentService.findById("1");
        return JSONUtil.JSONSuccessToString(departments);
    }

    /**
     * 根据组织的id获取该组织的详细信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail")
    public String returnDetail(@Param(value = "id") String  id){
        DepartDes departDes = departmentDesService.findById(id);
        return JSONUtil.JSONSuccessToString(departDes);
    }

    /**
     *获取所有的组织机构类型
     * @return
     */
    @RequestMapping(value = "/org")
    public String returnOrganization(){
        List<Depart> departs = departmentService.findAll();
        return JSONUtil.JSONSuccessToString(departs);
    }

    /**
     * 根据前端修改的部门详细信息修改
     * @param data
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String Print(@RequestBody DepartDes data){
//        List<Depart> departs = departmentService.findAll();
        DepartDes departDes = departmentDesService.findById(String.valueOf(data.getId()));

        Depart depart = departmentService.findDepartById(data.getId());
        depart.setName(data.getName());

        boolean isUpdate = data.equals(departDes);
        boolean update = departmentDesService.modifyDepartDes(data);
        if (update && !isUpdate) {
            departmentService.updateDepart(depart);
            return JSONUtil.JSONSuccessToString("修改成功");
        }
        else if (isUpdate)
            return JSONUtil.JSONSuccessToString("vacant");
        else
            return JSONUtil.JSONFailedToString("修改失败");
    }

    /**
     * 根据前端上下级节点的移动修改父子节点
     * @param parent
     * @param son
     * @return
     */
    @RequestMapping(value = "/updated")
    public String Print(@Param("parent") Integer parent,
                      @Param("son") Integer son){
        boolean update = departmentService.updateDep(parent,son);
        if (update)
            return JSONUtil.JSONSuccessToString("修改成功");
        else
            return JSONUtil.JSONFailedToString("修改失败");
    }


    @RequestMapping(value = "/delete")
    public String deleteDepart(@Param("id") Integer id){
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        ids.addAll(departmentService.findAllIdSubId(id));
        for (Integer integer : ids) {
            departmentService.deleteDepartById(integer);
            departmentDesService.deleteDepartDesById(integer);
        }
        return JSONUtil.JSONSuccessToString("删除成功");
    }
}
