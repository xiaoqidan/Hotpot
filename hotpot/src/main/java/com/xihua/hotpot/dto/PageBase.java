package com.xihua.hotpot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @date 2021/5/2 9:36
 */
@Data
public class PageBase {

    /**
     * 降序.
     */
    private static final String SORT_DESC = "-";

    /**
     * 每页条数.
     */
    private Integer limit;

    /**
     * 当前页.
     */
    private Integer offset;


}
