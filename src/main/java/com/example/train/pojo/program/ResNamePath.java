package com.example.train.pojo.program;

import com.example.train.pojo.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 资源名称和资源封面图片路径
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResNamePath {

    private Integer id;
    private String name;
    private String path;
    private String type;
//    private List<Book> bookList;

}
