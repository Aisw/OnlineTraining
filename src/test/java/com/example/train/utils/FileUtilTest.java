package com.example.train.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilTest {

    @Test
    void uploadDocument() {
    }

    @Test
    void deleteFile() {
        String path = "D:/OnlineTraining/src/main/resources/files/book/integral.png";
        System.out.println(FileUtil.deleteFile(path));
    }
}