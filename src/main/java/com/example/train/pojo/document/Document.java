package com.example.train.pojo.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * 文件管理中文件
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    private Integer id;
    private String name;
    private Date time;
    private String path;
    private String isPermit;
    private String type;
    private Double duration;
    private Double score;

}
