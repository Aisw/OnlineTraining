package com.example.train.pojo.program;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * 培训计划的简化版
 * name/type/time/status
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramDes {

    private String title;
    private String type;
    private Date time;
    private String status;

}
