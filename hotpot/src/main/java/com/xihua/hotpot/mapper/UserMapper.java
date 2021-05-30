package com.xihua.hotpot.mapper;

import com.xihua.hotpot.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 操作用户表接口
 */
@Mapper
public interface UserMapper {
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
    int insert(User record);

    /**
     * 根据id 查询
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 通过openId 查询
     * @param openId
     * @return
     */
    User selectByOpenId(String openId);

    @Select("select * from user where id = #{name}")
    User selectById(Integer name);
}