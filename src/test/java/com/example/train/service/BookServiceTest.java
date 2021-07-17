package com.example.train.service;

import com.example.train.OnlineTrainingApplication;
import com.example.train.pojo.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;;

import java.io.*;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

@SpringBootTest(classes = OnlineTrainingApplication.class)
class BookServiceTest {

    @Value("${bookPath}")
    String book;

    @Autowired
    private BookService bookService;

    @Test
    void findAll() {
        List<Book> books = bookService.findAll();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    void findByTimeAndName(){
        List<Book> books = bookService.findByNameAndTime("小",null);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    void addBook(){
        Book book = new Book();
        book.setName("蜡笔小新");
        book.setTime(new Date(2020,9,10));
        book.setPath("/pp");
        System.out.println(bookService.addBook(book));
    }

    @Test
    void deleteBookById(){
        System.out.println(bookService.deleteBookById(3));
    }

    @Test
    void modifyBook(){
        Book book = new Book(4,"蜡笔",new Date(System.currentTimeMillis()),"/home");
        System.out.println(bookService.modifyBook(book));
    }

    @Test
    void getPathById(){
        System.out.println(bookService.getPathById(1));
    }

    @Test
    void testPath() throws FileNotFoundException {
//        InputStream inputStream = getClass().getResourceAsStream("/files/book/file.png");
//        inputStream.read()
//        String path= ClassUtils.getDefaultClassLoader().getResource("files").getPath();
//        System.out.println(path);
        System.out.println(book);
        InputStream is =System.in;
        is = new BufferedInputStream(new FileInputStream(new File(book+"1.txt")));
        Scanner sc = new Scanner(is);
        System.out.println(sc.nextLine());
    }
}