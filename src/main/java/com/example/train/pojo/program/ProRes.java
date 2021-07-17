package com.example.train.pojo.program;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 培训与资料的关系
 */
public class ProRes {

    private Integer id;
    private Integer proId;
    private Integer resId;

}
