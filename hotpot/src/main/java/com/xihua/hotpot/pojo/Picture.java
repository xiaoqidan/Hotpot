package com.xihua.hotpot.pojo;

import lombok.Data;

@Data
public class Picture {
    private Integer id;

    private String name;

    private String location;

    private Byte type;

    private Byte sort;

    private Byte valid;

}