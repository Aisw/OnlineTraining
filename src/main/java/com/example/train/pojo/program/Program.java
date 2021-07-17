package com.example.train.pojo.program;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * 培训计划
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Program {

    private Integer id;
    private String title;
    private String type;
    private Date publishTime;
    private Date startTime;
    private Date endTime;
    private String status;
    private String scope;
    private String path;
    private String visible;

}
