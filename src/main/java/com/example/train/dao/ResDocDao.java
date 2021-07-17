package com.example.train.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 *  资源与文件的关联关系
 */
public interface ResDocDao {

    /**
     * 插入资源与文件的关联
     * @param resId
     * @param docId
     * @return
     */
    boolean insertResDoc(Integer resId,Integer docId);

    /**
     * 根据资源id删除资源于文件的关联
     * @param resId
     * @return
     */
    boolean deleteResDoc(Integer resId);

    /**
     * 根据resId从res_doc表中获得docIds
     * @param resId
     * @return
     */
    List<Integer> selectDocIdByResId(Integer resId);
}
