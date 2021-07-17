package com.example.train.dao;

import com.example.train.pojo.program.ProNamePath;
import com.example.train.pojo.program.Program;
import com.example.train.pojo.program.ProgramDes;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ProgramDao {

    /**
     * 根据id查询培训信息
     * @param id
     * @return
     */
    Program selectById(Integer id);

    /**
     * 查询所有的培训计划
     * @return
     */
    List<Program> selectAll();

    /**
     * 根据名称/类型/时间/状态查询培训计划
     * @param programDes
     * @return
     */
    List<Program> selectByProgramDes(ProgramDes programDes);

    /**
     * 根据id修改status
     * @param id
     * @param status
     * @return
     */
    boolean updateStatus(Integer id,String status);

    /**
     * 根据id删除培训计划
     * @param id
     * @return
     */
    boolean deleteProgram(Integer id);

    /**
     * 新建一个id值的program
     * @param program
     * @return
     */
    boolean insertProgram(Program program);

    /**
     * 根据id值修改培训
     * @param program
     * @return
     */
    boolean updateProgram(Program program);

    /**
     * 根据id修改路径
     * @param id
     * @param path
     * @return
     */
    boolean updateProgramPathById(Integer id,String path);

    /**
     * 推荐获取前几个培训
     * @return
     */
    List<ProNamePath> selectProNamePath();

    /**
     * 查询改用户的培训
     * @param empId
     * @return
     */
    List<ProNamePath> selectProNamePathByEmpId(Integer empId);
}
