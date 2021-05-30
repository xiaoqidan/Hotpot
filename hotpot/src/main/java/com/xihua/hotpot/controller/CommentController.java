package com.xihua.hotpot.controller;

import com.xihua.hotpot.consts.Const;
import com.xihua.hotpot.dto.CommentQueryDto;
import com.xihua.hotpot.pojo.Comment;
import com.xihua.hotpot.repository.ResponseDo;
import com.xihua.hotpot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 评论模块 对接前端
 * @author wufang
 */
@RestController
@RequestMapping(Const.hg + "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 获得所有评论 返回评论列表
     * @return
     */
    @GetMapping
    public ResponseDo getAll(){
        return commentService.getAll();
    }

    /**
     * 插入一条新的评论
     * @param comment
     */
    @PostMapping
    public void insert(@RequestBody Comment comment){
        commentService.insert(comment);
    }

    /**
     * 评论
     */
    @GetMapping("/list")
    public ResponseDo CommentList(CommentQueryDto queryDto) {
        return new ResponseDo(commentService.getList(queryDto));
    }
}
