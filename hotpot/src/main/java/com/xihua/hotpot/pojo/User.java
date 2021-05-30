package com.xihua.hotpot.pojo;

import lombok.Data;

/**
 * 用户熟悉
 */
@Data
public class User {
    private Integer id;

    /**
     * 小程序 用户唯一标识
     */
    private String openid;

    /**
     * 是否是店铺管理元 0不是 1是
     */
    private Boolean authority;

    /**
     * 用户名字
     */
    private String name;

    /**
     * 用户头像路径
     */
    private String url;

    private String password;
}