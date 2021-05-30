package com.xihua.hotpot.pojo;

import lombok.Data;

/**
 * 外卖地址
 */
@Data
public class Location {
    private Integer id;

    private Integer userId;
    /**
     * 地址
     */
    private String content;

    private String telephone;

    private String name;

    private Byte sex;
    /**
     * 是否有效
     */
    private Byte valid;
}