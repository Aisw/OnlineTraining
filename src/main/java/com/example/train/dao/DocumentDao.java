package com.example.train.dao;

import com.example.train.pojo.document.Document;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface DocumentDao {

    /**
     * 根据id值查询文件
     * @param id
     * @return
     */
    Document selectById(Integer id);

    /**
     * 获取所有的文件列表
     * @return
     */
    List<Document> selectAll();

    /**
     * 根据名称和时间模糊查询
     * @param name
     * @param time
     * @return
     */
    List<Document> selectByNameAndTime(String name, Date time);

    /**
     * 添加电子书
     * @param document
     * @return
     */
    boolean insertDocument(Document document);

    /**
     * 通过id删除文件
     * @param id
     * @return
     */
    boolean deleteDocumentById(Integer id);

    /**
     * 修改文件
     * @param document
     * @return
     */
    Integer updateDocument(Document document);

    /**
     * 根据id获取文件路径
     * @param id
     * @return
     */
    String selectPathById(Integer id);

    /**
     * 根据id查询文件名称
     * @param id
     * @return
     */
    String selectNameById(Integer id);

    /**
     * 通过资源id查询文件
     * @param resId
     * @return
     */
    Document selectByResId(Integer resId);
}
