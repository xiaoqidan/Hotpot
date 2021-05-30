package com.xihua.hotpot.mapper;

import com.xihua.hotpot.dto.CommentQueryDto;
import com.xihua.hotpot.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 操作评论表 接口
 */
@Mapper
public interface CommentMapper {
    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入
     * @param record
     * @return
     */
    int insert(Comment record);

    /**
     * 查找
     * @param id
     * @return
     */
    Comment selectByPrimaryKey(Integer id);

    /**
     * 查找全部
     * @return
     */
    List<Comment> selectAll();

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Comment record);

    List<Comment> selectList(CommentQueryDto queryDto);
}