package com.xihua.hotpot.pojo;

import lombok.Data;

/**
 * 火锅店铺信息
 */
@Data
public class Store {
    private Integer id;

    private String name;

    private String location;

    private String js;
}