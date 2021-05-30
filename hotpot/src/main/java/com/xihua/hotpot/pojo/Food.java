package com.xihua.hotpot.pojo;

import lombok.Data;

/**
 * 菜
 */
@Data
public class Food {
    private Integer id;

    /**
     * 分类目录的id
     */
    private String categoryId;

    /**
     * 分类目录的名称
     */
    private String categoryName;


    private String name;

    private Integer size;

    /**
     * 菜的图片地址
     */
    private String imgUrl;

    private Byte valid;

    /**
     * 单位
     */
    private String unit;

    /**
     * 价格
     */
    private Integer price;

    private Byte type;

    /**
     * 销量
     */
    private Integer number;

    private Integer tj;
}