package com.example.train.pojo.program;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 培训的名称和资料封面路径
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProNamePath {

    private Integer id;
    private String name;
    private String path;
    private List<ResNamePath> resNamePathList;

}
