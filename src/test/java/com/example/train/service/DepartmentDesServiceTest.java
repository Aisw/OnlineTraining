package com.example.train.service;

import com.example.train.OnlineTrainingApplication;
import com.example.train.pojo.depart.DepartDes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = OnlineTrainingApplication.class)
class DepartmentDesServiceTest {

    @Autowired
    private DepartmentDesService departmentDesService;
    @Test
    void findById() {
        System.out.println(departmentDesService.findById("2"));
    }

    @Test
    void modifyDepartDes(){
        DepartDes departDes = new DepartDes();
        departDes.setId(6);
        departDes.setName("班组四");
        departDes.setType("班组四");
        departDes.setDescription("班组四");

        System.out.println(departmentDesService.modifyDepartDes(departDes));
    }

    @Test
    void deleteDepartDesById(){
        System.out.println(departmentDesService.deleteDepartDesById(9));
    }
}