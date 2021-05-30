package com.xihua.hotpot.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.util.StringUtil;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单
 */
@Data
public class Order {
    private Integer id;

    private Integer userId;

    private String userName;
    /**
     * 订单总额
     */
    private Float money;

    /**
     * 备注
     */
    private String bz;

    /**
     * 外卖：地址
     */
    private Integer locationId;
    private Location location;

    /**
     * 订单类型 1：本店；2：外卖
     */
    private Byte type;

    /**
     * 本店铺 桌号
     */
    private Integer tableNumber;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date createTime;

    /**
     * 订单包含菜品
     */
    List<OrderContent> foods;

    private String content;

    private Integer status;

    private String psy;
    private String psyPhone;
}