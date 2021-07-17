package com.example.train.pojo.program;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 资源与文件的绑定res_id
 */
public class ResDoc {

    private Integer id;
    private Integer resId;
    private Integer docId;
}
