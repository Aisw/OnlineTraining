package com.example.train.pojo.program;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 资料详细信息
 */
public class ResourceDetail {

    private Resource resource;
    private List<String> docNames;

}
