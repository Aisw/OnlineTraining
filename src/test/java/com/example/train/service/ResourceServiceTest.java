package com.example.train.service;

import com.example.train.OnlineTrainingApplication;
import com.example.train.pojo.program.ResNamePath;
import com.example.train.pojo.program.Resource;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = OnlineTrainingApplication.class)
public class ResourceServiceTest {

    @Autowired
    private ResourceService resourceService;

    @Test
    public void addResource() {
        Resource resource = new Resource();
        System.out.println(resourceService.addResource(resource));
        System.out.println(resource.getId());
    }

    @Test
    void modifyResource(){
//        Resource resource = new Resource(3,"ceshi ",
//                "doc","/home");
//        System.out.println(resourceService.modifyResource(resource));
    }

    @Test
    void deleteResource(){
        System.out.println(resourceService.deleteResource(2));
    }

    @Test
    void modifyPathById(){
        System.out.println(resourceService.modifyPathById(14, "q.png"));
    }

    @Test
    void getPathById(){
        System.out.println(resourceService.getPathById(143));
    }

    @Test
    void getByResId(){
        System.out.println(resourceService.getByResId(213));
    }

    @Test
    void getResNamePathByProId(){
        List<ResNamePath> resNamePathList = resourceService.getResNamePathByProId(156);
        for (ResNamePath resNamePath : resNamePathList) {
            System.out.println(resNamePath);
        }
    }
}