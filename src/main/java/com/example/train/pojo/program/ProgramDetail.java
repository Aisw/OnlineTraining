package com.example.train.pojo.program;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

/**
 * 培训计划详细信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramDetail {

    private Program program;
    private List<ResourceDetail> resourceDetails;

}
