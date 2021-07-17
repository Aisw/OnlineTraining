package com.example.train.dao;

import com.example.train.pojo.document.DocBook;
import org.springframework.stereotype.Repository;

/**
 * 关于文件与电子书关联关系
 */
@Repository
public interface DocBookDao {

    /**
     * 插入文件与电子书关联关系
     * @param docBook
     * @return
     */
    boolean insertDocBook(DocBook docBook);

}
