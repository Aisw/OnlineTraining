package com.example.train.service;

import com.example.train.dao.ResDocDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResDocService {

    @Autowired
    private ResDocDao resDocDao;

    /**
     * 添加资源与文件的关联
     * @param resId
     * @param docId
     * @return
     */
    public boolean addResDoc(Integer resId,Integer docId){
        return resDocDao.insertResDoc(resId,docId);
    }

    /**
     * 根据资源id删除文件的关联
     * @param resId
     * @return
     */
    public boolean deleteResDoc(Integer resId){
        return resDocDao.deleteResDoc(resId);
    }

    /**
     * 根据resId从res_doc表中获得doc_id
     * @param resId
     * @return
     */
    public List<Integer> findDocIdsByResId(Integer resId){
        return resDocDao.selectDocIdByResId(resId);
    }
}
