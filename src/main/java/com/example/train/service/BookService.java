package com.example.train.service;

import com.example.train.dao.BookDao;
import com.example.train.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    /**
     * 获取所有的电子书列表
     * @return
     */
    public List<Book> findAll(){
        return bookDao.selectAll();
    }

    /**
     * 根据名称模糊查询，时间查询
     * @param name
     * @param time
     * @return
     */
    public List<Book> findByNameAndTime(String name, String time){
        return bookDao.selectByNameAndTime(name,time);
    }

    /**
     * 添加电子书
     * @param book
     * @return
     */
    public boolean addBook(Book book){
        return bookDao.insertBook(book);
    }

    /***
     * 通过id删除电子书
     * @param id
     * @return
     */
    public boolean deleteBookById(Integer id){
        return bookDao.deleteBookById(id);
    }

    /**
     * 更新电子书
     * @param book
     * @return
     */
    public Integer modifyBook(Book book){
        return bookDao.updateBook(book);
    }

    /**
     * 根据id获取文件路径
     * @param id
     * @return
     */
    public String getPathById(Integer id){
        return bookDao.selectPathById(id);
    }

}
