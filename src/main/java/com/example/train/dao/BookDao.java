package com.example.train.dao;

import com.example.train.pojo.Book;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface BookDao {

    /**
     * 获取电子书列表
     * @return
     */
    List<Book> selectAll();

//    /**
//     * 根据id查询电子书
//     * @param id
//     * @return
//     */
//    Book selectById(Integer id);

    /**
     * 根据名称和时间模糊查询
     * @param name
     * @param time
     * @return
     */
    List<Book> selectByNameAndTime(String name,String time);

    /**
     * 添加电子书
     * @param book
     * @return
     */
    boolean insertBook(Book book);

    /**
     * 通过id删除电子书
     * @param id
     * @return
     */
    boolean deleteBookById(Integer id);

    /**
     * 修改电子书
     * @param book
     * @return
     */
    Integer updateBook(Book book);

    /**
     * 根据id获取文件路径
     * @param id
     * @return
     */
    String selectPathById(Integer id);

    /**
     * 通过文件id查询绑定的电子书
     * @param docId
     * @return
     */
    List<Book> selectByDocId(Integer docId);
}
