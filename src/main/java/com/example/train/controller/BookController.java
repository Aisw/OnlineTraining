package com.example.train.controller;

import com.example.train.pojo.Book;
import com.example.train.service.BookService;
import com.example.train.utils.FileUtil;
import com.example.train.utils.JSONUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * 关于电子书的请求控制层
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Value("${bookPath}")
    String prefix ;
//    = "D:/OnlineTraining/src/main/resources/files/book/"

    @Autowired
    private BookService bookService;

    /**
     * 获取电子书列表
     * @return
     */
    @RequestMapping(value = "/shows")
    public String retBooks(){
        List<Book> books = bookService.findAll();
        return JSONUtil.JSONSuccessToString(books);
    }

    /**
     * 根据名字和发布时间模糊查询
     * @param name
     * @param time
     * @return
     */
    @RequestMapping(value = "/show")
    public String retShowooks(@Param("name") String name,@Param("time") String time){
        List<Book> books = new ArrayList<>();
        if (time.isEmpty())
            books = bookService.findByNameAndTime(name,time);
        else
            books = bookService.findByNameAndTime(name,time.substring(0,10));
        return JSONUtil.JSONSuccessToString(books);
    }

    /**
     * 添加电子书
     * @param file
     * @param name
     * @return
     */
    @RequestMapping(value = "/upload")
    public String addBook(@RequestParam("file")MultipartFile file,
                          @RequestParam("bookName") String name){
        Date date = new Date(System.currentTimeMillis());
        System.out.println(date);

        String path = file.getOriginalFilename();

        Book book = new Book();
        book.setName(name);
        book.setTime(date);
        book.setPath(path);

        boolean addBook = bookService.addBook(book);

//        String prefix = "D:/OnlineTraining/src/main/resources/files/book/";
        String message = FileUtil.uploadDocument(prefix,file);

        if (addBook)
            return JSONUtil.JSONSuccessToString(message);
        else
            return JSONUtil.JSONFailedToString("上传错误");
    }

    /**
     * 根据id删除电子书
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    public String retDeleteBook(@Param("id") Integer id){
        boolean deleteBook = bookService.deleteBookById(id);
        if (deleteBook)
            return JSONUtil.JSONSuccessToString("删除成功");
        else
            return JSONUtil.JSONFailedToString("删除失败");
    }

    /**
     * 上传修改电子书
     * @param file
     * @param id
     * @param name
     * @return
     */
    @RequestMapping(value = "/update")
    public String retUpdate(@RequestParam("file")MultipartFile file,
                            @RequestParam("id")Integer id,
                            @RequestParam("name")String name){


        //修改附件时先删除之前上传文件
        String deletePath = bookService.getPathById(id);
        FileUtil.deleteFile(prefix+deletePath);

        Date nowDate = new Date(System.currentTimeMillis());
        String path = file.getOriginalFilename();

        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setTime(nowDate);
        book.setPath(path);
        bookService.modifyBook(book);

        System.out.println(book);
        //附件写入
        String message = FileUtil.uploadDocument(prefix,file);

        return JSONUtil.JSONSuccessToString(message);
    }

    /**
     * 根据路径下载电子书
     * @param id
     * @param response
     */
    @CrossOrigin
    @RequestMapping(value = "/load")
    public void load(@Param("id")Integer id,HttpServletResponse response){
        String path = bookService.getPathById(id);
//        System.out.println(path);
        FileUtil.download(prefix+path,response);
    }
}
