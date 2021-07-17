package com.example.train.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * 电子书
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Integer id;
    private String name;
    private Date time;
    private String path;
}
