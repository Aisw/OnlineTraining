package com.example.train.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 * 培训与资料的关联关系
 */
public interface ProResDao {

    /**
     * 添加培训与资料的关联
     * @param proId
     * @param resId
     * @return
     */
    boolean insertProRes(Integer proId,Integer resId);

    /**
     * 根据proId从pro_res表中查询resIds
     * @param proId
     * @return
     */
    List<Integer> selectResIdsByProId(Integer proId);
}
