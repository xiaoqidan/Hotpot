package com.xihua.hotpot.pojo;

import lombok.Data;

import java.util.List;

/**
 * 分类目录
 */
@Data
public class Category {
    private Integer id;

    private Integer storeId;

    /**
     * 名字
     */
    private String name;

    /**
     * 是否有效
     */
    private Byte valid;

    /**
     * 排序
     */
    private Byte sort;

    /**
     * 菜品数量
     */
    private Integer number;

    /**
     * 包含的菜品
     */
    private List<Food> foods;

    private Integer type;
}