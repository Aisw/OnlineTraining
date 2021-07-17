package com.example.train.pojo.program;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 资料
 */
public class Resource {

    private Integer id;
    private String name;
    private String type;
    private String path;
}
