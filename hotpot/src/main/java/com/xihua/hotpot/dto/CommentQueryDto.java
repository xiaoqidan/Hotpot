package com.xihua.hotpot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author hfy
 * @date 2021/5/9 15:39
 */
@Data
public class CommentQueryDto extends PageBase {
    private String name;

    private Integer type;

    private Integer status;
}
