package com.example.train.service;

import com.example.train.dao.ProResDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * 培训与资料的 处理关系
 */
public class ProResService {

    @Autowired
    private ProResDao proResDao;

    /**
     * 为pro_res添加新的培训与资料关系
     * @param proId
     * @param resId
     * @return
     */
    public boolean addProRes(Integer proId,Integer resId){
        return proResDao.insertProRes(proId,resId);
    }

    /**
     * 根据pro_id查询res_ids
     * @param proId
     * @return
     */
    public List<Integer> getResIdsByProId(Integer proId){
        return proResDao.selectResIdsByProId(proId);
    }
}
