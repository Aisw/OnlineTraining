package com.example.train.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * 积分
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {

    private Integer id;
    private Integer empId;
    private Integer docId;
    private String name;
    private Date time;
    private Double score;
}
