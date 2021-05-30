package com.xihua.hotpot.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderContent {
    private Integer id;

    /**
     * 订单Id
     */
    private Integer orderId;

    /**
     * 菜品id
     */
    private Integer foodId;

    private Integer number;

    private Float price;

    private String name;
}