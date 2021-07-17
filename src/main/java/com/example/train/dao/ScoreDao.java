package com.example.train.dao;

import com.example.train.pojo.Score;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreDao {

    /**
     * 添加积分项
     * @param score
     * @return
     */
    boolean insertScore(Score score);

    /**
     * 获取个人的积分
     * @param empId
     * @return
     */
    double selectTotalByEmpId(Integer empId);

    /**
     * 查询用户积分的详细记录
     * @param empId
     * @return
     */
    List<Score> selectByEmpId(Integer empId);
}
