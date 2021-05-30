package com.xihua.hotpot.mapper;

import com.github.pagehelper.PageInfo;
import com.xihua.hotpot.dto.FoodQueryDto;
import com.xihua.hotpot.pojo.Food;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 操作菜单表接口
 */
@Mapper
public interface FoodMapper {
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
    int insert(Food record);

    /**
     * 根据id 查找
     * @param id
     * @return
     */
    Food selectByPrimaryKey(Integer id);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Food record);

    /**
     * 根据目录id 查找food
     * @param categoryId
     * @return
     */
    List<Food> selectByCategoryId(Integer categoryId);

    /**
     * 获得推荐food
     * @return
     */
    List<Food> getTjFood();

    /**
     * 获得全部food
     * @return
     */
    List<Food> selectList(Integer type);

    /**
     * 更新销售量
     * @param foodId
     */
    @Update("update food set number = number +1 where id = #{foodId}")
    void addNumber(Integer foodId);

    List<Food> selectFoods(FoodQueryDto queryDto);
}