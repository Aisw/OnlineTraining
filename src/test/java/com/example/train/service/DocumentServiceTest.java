package com.example.train.service;

import com.example.train.OnlineTrainingApplication;
import com.example.train.pojo.document.DocBook;
import com.example.train.pojo.document.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = OnlineTrainingApplication.class)
class DocumentServiceTest {

    @Autowired
    private DocumentService documentService;

    @Test
    void findById(){
        System.out.println(documentService.findById(58));
    }

    @Test
    void findAll() {
        List<Document> documents = documentService.findAll();
        for (Document document : documents) {
            System.out.println(document);
        }
    }

    @Test
    void addDocBook(){
        DocBook docBook = new DocBook();
        docBook.setDocId(1);
        docBook.setBookId(1);
        documentService.addDocBook(docBook);
    }

    @Test
    void findNameById(){
        System.out.println(documentService.findNameById(1));
    }

    @Test
    void getByResId(){
        System.out.println(documentService.findByResId(189));
    }
}