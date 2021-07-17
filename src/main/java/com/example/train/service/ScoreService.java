package com.example.train.service;

import com.example.train.dao.ScoreDao;
import com.example.train.pojo.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {

    @Autowired
    private ScoreDao scoreDao;

    /**
     * 添加积分项
     * @param score
     * @return
     */
    public boolean addScore(Score score){
        return scoreDao.insertScore(score);
    }

    /**
     * 给用户查询全部积分
     * @param empId
     * @return
     */
    public Double getTotalByEmpId(Integer empId){
        return  scoreDao.selectTotalByEmpId(empId);
    }

    public List<Score> getScoresByEmpId(Integer empId){
        return scoreDao.selectByEmpId(empId);
    }
}
