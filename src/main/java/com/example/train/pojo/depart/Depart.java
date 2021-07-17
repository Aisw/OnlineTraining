package com.example.train.pojo.depart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Depart {
    private Integer id;
    private Integer superId;
    private String name;
}
