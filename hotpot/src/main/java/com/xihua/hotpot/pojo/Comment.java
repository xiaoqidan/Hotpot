package com.xihua.hotpot.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 评论
 */
@Data
public class Comment {
    private Integer id;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    private Integer userId;
    /**
     * 是否删除（隐藏）
     */
    private Integer hide;

    /**
     * 评分
     */
    private Byte star;
    /**
     * 评价类型，可为空
     */
    private String type;

    private String name;

    private String url;

    private String back;

    private Integer status;

    private Integer orderId;
}