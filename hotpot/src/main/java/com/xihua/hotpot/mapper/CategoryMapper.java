package com.xihua.hotpot.mapper;

import com.github.pagehelper.PageInfo;
import com.xihua.hotpot.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 目录 操控数据表接口
 */
@Mapper
public interface CategoryMapper {
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
    int insert(Category record);

    /**
     * 根据组件 选择一个目录
     * @param id
     * @return
     */
    Category selectByPrimaryKey(Integer id);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Category record);

    /**
     * 根据店铺id 返回目录列表
     * @param storeId
     * @return
     */
    List<Category> selectByStoreId(@Param("storeId") Integer storeId,@Param("type") Integer type);

    List<Category> selectByStoreIdAll(@Param("storeId") Integer storeId,@Param("type") Integer type);

    /**
     * 更新是否有效
     * @param id
     */
    @Update("update category set valid = !valid where id = #{id}")
    void updateValid(Integer id);
}