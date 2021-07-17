package com.example.train.pojo.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件和电子书关联关系
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocBook {

    private Integer id;
    private Integer docId;
    private Integer bookId;

}
