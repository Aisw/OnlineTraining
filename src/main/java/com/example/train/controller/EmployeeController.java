package com.example.train.controller;

import com.example.train.pojo.depart.DepartDes;
import com.example.train.pojo.employee.Employ;
import com.example.train.pojo.employee.EmployIdentity;
import com.example.train.pojo.employee.Employee;
import com.example.train.security.pojo.Role;
import com.example.train.security.service.RoleService;
import com.example.train.service.DepartmentDesService;
import com.example.train.service.EmployeeService;
import com.example.train.utils.FileUtil;
import com.example.train.utils.JSONUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/emp")
public class EmployeeController {

    @Value("${imgPath}")
    private String prefix ;

    @Value("${url}")
    private String url;
//    = "D:/OnlineTraining/src/main/resources/files/cards/"

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentDesService departmentDesService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;
    /**
     * 获取员工列表
     * @param index
     * @return
     */
    @RequestMapping(value = "/shows")
    public String retEmployees(@Param("index") Integer index){
        List<Employee> employees = employeeService.findByDepartId(index);
        return JSONUtil.JSONSuccessToString(employees);
    }

    /**
     * 根据用户姓名、性别、是否管理员查询用户
     * @param employ
     * @return
     */
    @RequestMapping(value = "/show" ,method = RequestMethod.POST)
    public String returnEmploys(@RequestBody Employ employ){
        List<Employee> employees = employeeService.findByEmploy(employ);
        return JSONUtil.JSONSuccessToString(employees);
    }

    /**
     * 获取id值
     * @return
     */
    @RequestMapping(value = "getId")
    public String getEmployeeId(){
        Employee employee = new Employee();
        employee.setIsAdministrator("否");
        employeeService.addEmployee(employee);

        Integer id = employee.getId();
        return JSONUtil.JSONSuccessToString(id);
    }

    /**
     * 添加新用户Employee
     * @param employee
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addEmployee(@RequestBody Employee employee){
        System.out.println(employee);
        String password = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(password);
        Role role = new Role();
        role.setAccount(employee.getPhone());
        role.setRole("user");
        roleService.addRole(role);
//        System.out.println(employee);
        switch (employee.getIsAdministrator()){
            case "部门管理员" :
                role.setRole("admin");
                roleService.addRole(role);
                break;
            case "集团管理员":
                role.setRole("root");
                roleService.addRole(role);
                break;
        }
        DepartDes departDes =departmentDesService.findById(String.valueOf(employee.getDepartId()));
        employee.setUnit(departDes.getName());
        System.out.println(employee);
        boolean add = employeeService.modifyEmployee(employee);
        return JSONUtil.JSONSuccessToString("添加成功");
    }

    /**
     * 根据前端修改employee数据
     * @param employee
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String modifyEmployee(@RequestBody Employee employee){
        boolean modify = employeeService.modifyEmployee(employee);
        if (modify)
            return JSONUtil.JSONSuccessToString("修改成功");
        else
            return JSONUtil.JSONFailedToString("修改失败");
    }

    @RequestMapping(value = "/delete")
    public String deleteEmployee(@Param("id") Integer id){

        List<String> paths = employeeService.findPathByEmpId(id);

        for (String path : paths) {
            FileUtil.deleteFile(prefix + path);
        }
        employeeService.deleteEmpIdebyEmpId(id);

        boolean delete = employeeService.deleteEmployeeById(id) ;
        if (delete)
            return JSONUtil.JSONSuccessToString("删除成功");
        else
            return JSONUtil.JSONFailedToString("删除失败");
    }

    /**
     * 上传身份证照片
     * @param file
     * @param id
     * @return
     */
    @RequestMapping(value = "/upload")
    public String uploadDocument(@RequestParam("file") MultipartFile file,
                                  @RequestParam("id") Integer id){
        System.out.println(id);
        String originalFilename = file.getOriginalFilename();
        String message = FileUtil.uploadDocument(prefix,file);
        System.out.println(message+"图片插入");
        EmployIdentity employIdentity = new EmployIdentity();
        employIdentity.setEmpId(id);
        employIdentity.setPath(originalFilename);
        boolean uploadEmployee = employeeService.uploadIdentityphoto(employIdentity);
        System.out.println(uploadEmployee);

        if (uploadEmployee)
            return JSONUtil.JSONSuccessToString(url+":8888/ide/"+originalFilename);
        else
            return JSONUtil.JSONFailedToString("上传失败");
    }

    @RequestMapping(value = "/getImgUrl")
    public String getEmployeeImgUrl(@RequestParam("empId") Integer empId){

        List<String> imgs = employeeService.findPathByEmpId(empId);
        System.out.println(imgs);
        List<String> imgUrls = new ArrayList<>();
        for (String img : imgs) {
            imgUrls.add(url+":8888/ide/"+img);
            System.out.println(img);
        }

        return JSONUtil.JSONSuccessToString(imgUrls);
    }
}
