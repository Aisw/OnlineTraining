package com.example.train.service;

import com.example.train.dao.DocBookDao;
import com.example.train.dao.DocumentDao;
import com.example.train.pojo.document.DocBook;
import com.example.train.pojo.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class DocumentService {

    //文件
    @Autowired
    private DocumentDao documentDao;

    //文件与电子书
    @Autowired
    private DocBookDao docBookDao;

    /**
     * 根据id查询文件
     * @param id
     * @return
     */
    public Document findById(Integer id){
        return documentDao.selectById(id);
    }

    /**
     * 查询所有文件
     * @return
     */
    public List<Document> findAll(){
        return documentDao.selectAll();
    }

    /**
     * 根据名称模糊查询，时间查询
     * @param name
     * @param time
     * @return
     */
    public List<Document> findByNameAndTime(String name, Date time){
        return documentDao.selectByNameAndTime(name,time);
    }

    /**
     * 添加文件
     * @param document
     * @return
     */
    public boolean addDocument(Document document){
        return documentDao.insertDocument(document);
    }

    /***
     * 通过id删除文件
     * @param id
     * @return
     */
    public boolean deleteDocumentById(Integer id){
        return documentDao.deleteDocumentById(id);
    }

    /**
     * 更新文件
     * @param document
     * @return
     */
    public Integer modifyDocument(Document document){
        return documentDao.updateDocument(document);
    }

    /**
     * 根据id获取文件路径
     * @param id
     * @return
     */
    public String getPathById(Integer id){
        return documentDao.selectPathById(id);
    }

    /**
     * 插入文件与电子书
     * @param docBook
     * @return
     */
    public boolean addDocBook(DocBook docBook){
        return docBookDao.insertDocBook(docBook);
    }

    /**
     * 根据id查询获得文件名称
     * @param id
     * @return
     */
    public String findNameById(Integer id){
        return documentDao.selectNameById(id);
    }

    /**
     * 通过资源id查询文件
     * @param resId
     * @return
     */
    public Document findByResId(Integer resId){
        return documentDao.selectByResId(resId);
    }
}
