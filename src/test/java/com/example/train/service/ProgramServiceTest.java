package com.example.train.service;

import com.example.train.OnlineTrainingApplication;
import com.example.train.pojo.program.ProNamePath;
import com.example.train.pojo.program.Program;
import com.example.train.pojo.program.ProgramDes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(classes = OnlineTrainingApplication.class)
class ProgramServiceTest {

    @Autowired
    private ProgramService programService;

    @Test
    void findById(){
        System.out.println(programService.findById(1));
    }

    @Test
    void findAll() {
        List<Program> programs = programService.findAll();
        for (Program program : programs) {
            System.out.println(program);
        }

        List<String> courseIds=  programs.stream()
                .filter(program -> !"".equals(program.getPath())&&null != program.getPath())
                .limit(6)
                .map(Program::getPath).collect(Collectors.toList());
        for (String courseId : courseIds) {
            System.out.println(courseId);
        }
    }

    @Test
    void findByProgramDes(){
        ProgramDes programDes = new ProgramDes();
        programDes.setTitle("督导学习第一期");

        List<Program> programs = programService.findByProgramDes(programDes);
        for (Program program : programs) {
            System.out.println(program);
        }
    }

    @Test
    void modifyStatus(){
        System.out.println(programService.modifyStatus(1, "未发布"));
    }

    @Test
    void deleteProgram(){
        System.out.println(programService.deleteProgram(3));
    }

    @Test
    void addProgram(){
        Program program = new Program();
        program.setStatus("未发布");

        programService.addProgram(program);
        System.out.println(program.getId());
    }

    @Test
    void modifyProgram(){
        Program program = new Program();
        program.setId(6);
        program.setTitle("督导学习三七");
        programService.modifyProgram(program);
    }

    @Test
    void modifyProgramPath(){
        System.out.println(programService.modifyProgramPath(17, "/program/1.png"));
    }

    @Test
    void getProNamePath(){
        List<ProNamePath> proNamePaths = programService.getProNamePath();
        for (ProNamePath proNamePath : proNamePaths) {
            System.out.println(proNamePath);
        }
    }

    @Test
    void getProNamePathByEmpId(){
        List<ProNamePath> proNamePaths = programService.getProNamePathByEmpId(1);
        for (ProNamePath proNamePath : proNamePaths) {
            System.out.println(proNamePath);
        }
    }
}