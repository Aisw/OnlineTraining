package com.example.train.service;

import com.example.train.dao.ProgramDao;
import com.example.train.pojo.program.ProNamePath;
import com.example.train.pojo.program.Program;
import com.example.train.pojo.program.ProgramDes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {

    @Autowired
    private ProgramDao programDao;

    /**
     * 根据id查询培训信息
     * @param id
     * @return
     */
    public Program findById(Integer id){
        return programDao.selectById(id);
    }

    /**
     * 获得所有培训计划
     * @return
     */
    public List<Program> findAll(){
        return programDao.selectAll();
    }

    /**
     * 通过简化的Program查询
     * @param programDes
     * @return
     */
    public List<Program> findByProgramDes(ProgramDes programDes){
        return programDao.selectByProgramDes(programDes);
    }

    /**
     * 根据id修改状态
     * @param id
     * @param status
     * @return
     */
    public boolean modifyStatus(Integer id,String status){
        return programDao.updateStatus(id,status);
    }

    /**
     * 根据id删除培训计划
     * @param id
     * @return
     */
    public boolean deleteProgram(Integer id){
        return programDao.deleteProgram(id);
    }

    /**
     * 插入program目的是获取id值
     * @param program
     * @return
     */
    public boolean addProgram(Program program){
        return programDao.insertProgram(program);
    }

    /**
     * 修改培训
     * @param program
     * @return
     */
    public boolean modifyProgram(Program program){
        return programDao.updateProgram(program);
    }

    /**
     * 根据id修改路径
     * @param id
     * @param path
     * @return
     */
    public boolean modifyProgramPath(Integer id,String path){
        return programDao.updateProgramPathById(id,path);
    }

    /**
     * 获取培训及其简单的资源
     * @return
     */
    public List<ProNamePath> getProNamePath(){
        return programDao.selectProNamePath();
    }

    /**
     * 查询该用户下的培训
     * @param empId
     * @return
     */
    public List<ProNamePath> getProNamePathByEmpId(Integer empId){
        return programDao.selectProNamePathByEmpId(empId);
    }

}
