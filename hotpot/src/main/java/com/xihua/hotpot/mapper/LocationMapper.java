package com.xihua.hotpot.mapper;

import com.xihua.hotpot.pojo.Location;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 操作地址表接口
 */
@Mapper
public interface LocationMapper {
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
    int insert(Location record);

    /**
     * 根据id 查询
     * @param id
     * @return
     */
    Location selectByPrimaryKey(Integer id);

    /**
     * 通过用户id 查询
     * @param userId
     * @return
     */
    List<Location> selectByUserId(Integer userId);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Location record);

    /**
     * 清除默认值，重新赋值
     * @param userId
     */
    void clearDefault(Integer userId);
}