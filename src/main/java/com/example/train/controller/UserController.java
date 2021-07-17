package com.example.train.controller;

import com.example.train.pojo.User;
import com.example.train.pojo.employee.Employee;
import com.example.train.service.EmployeeService;
import com.example.train.service.UserService;
import com.example.train.utils.JSONUtil;
import com.example.train.utils.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 解析token获取用户信息
     * @param token
     * @return
     */
    //url地址:xxx.xx.xx.xx:xxxx/user/id
    @RequestMapping(value = "/user")
    public String getPersonalInformation(@RequestHeader("token") String token){
        Integer id = Integer.parseInt(JWTTokenUtil.getMemberIdByJwtToken(token));
        Employee employee = employeeService.findById(id);
//        User user = userService.findById(id);

        return JSONUtil.JSONToString(
                employee!=null,employee,"查询失败","GET_ARTICLE_FAILED");
    }
}
