package com.xihua.hotpot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xihua.hotpot.dto.CommentQueryDto;
import com.xihua.hotpot.mapper.CommentMapper;
import com.xihua.hotpot.mapper.OrderMapper;
import com.xihua.hotpot.pojo.Comment;
import com.xihua.hotpot.pojo.Order;
import com.xihua.hotpot.repository.ResponseDo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 评论 service
 * @author wufang
 */
@Service
public class CommentService {
    /**
     * 与comment数据表对接的接口
     */
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private OrderService orderService;

    /**
     * 获得所有评论
     * @return
     */
    public ResponseDo getAll(){
        return new ResponseDo(commentMapper.selectAll());
    }

    /**
     * 插入一个新的评论
     * @param comment
     */
    public void insert(Comment comment) {
        if(comment.getId()!=null){
            comment.setStatus(1);
            commentMapper.updateByPrimaryKeySelective(comment);
        }else {
            comment.setHide(0);
            comment.setCreateTime(new Date());
            commentMapper.insert(comment);
            Order order = new Order();
            order.setId(comment.getOrderId());
            order.setStatus(4);
            orderService.updateOrder(order);
        }
    }

    public PageInfo<Comment> getList(CommentQueryDto queryDto) {
        int page = queryDto.getOffset() / queryDto.getLimit() + 1;
        PageHelper.startPage(page, queryDto.getLimit());
        List<Comment> comments = commentMapper.selectList(queryDto);
        PageInfo pageInfo = new PageInfo<>(comments);
        return pageInfo;
    }
}
