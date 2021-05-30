package com.xihua.hotpot.dto;

import com.github.pagehelper.Page;
import lombok.Data;

/**
 * @author hfy
 * @date 2021/5/9 10:53
 */
@Data
public class FoodQueryDto extends PageBase {
    private String name;
    private Integer categoryId;
    private Integer tj;
    private Integer type;
    private Integer valid;
}
