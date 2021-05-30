package com.xihua.hotpot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xihua.hotpot.dto.FoodQueryDto;
import com.xihua.hotpot.mapper.CategoryMapper;
import com.xihua.hotpot.mapper.FoodMapper;
import com.xihua.hotpot.pojo.Food;
import com.xihua.hotpot.repository.ResponseDo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜品
 * @author wufang
 */
@Service
public class FoodService {
    /**
     * 与food表关联的接口
     */
    @Resource
    private FoodMapper foodMapper;

    @Resource
    private CategoryMapper categoryMapper;
    /**
     * 根据目录id 查询下面的菜品
     * @param categoryId
     * @return
     */
    public ResponseDo getFood(Integer categoryId){
        return new ResponseDo(foodMapper.selectByCategoryId(categoryId));
    }

    public ResponseDo getTjFood() {
        return new ResponseDo(foodMapper.getTjFood());
    }

    public void update(Food food) {
        foodMapper.updateByPrimaryKeySelective(food);
    }

    /**
     * 如果id 存在则更新，不存在则插入
     * @param food
     */
    public void insert(Food food) {
        if(food.getId()!=null){
            foodMapper.updateByPrimaryKeySelective(food);
        }else{
            foodMapper.insert(food);
        }
    }

    public ResponseDo getFoodById(Integer id) {
        return new ResponseDo(foodMapper.selectByPrimaryKey(id));
    }


    public PageInfo<Food> getFoods(FoodQueryDto queryDto) {
        int page=queryDto.getOffset() / queryDto.getLimit()+1;
        PageHelper.startPage(page, queryDto.getLimit());
        List<Food> foods = foodMapper.selectFoods(queryDto);
        for(Food food:foods){
            if(food.getCategoryId()!=null) {
                food.setCategoryName(categoryMapper.selectByPrimaryKey(Integer.valueOf(food.getCategoryId())).getName());
            }
        }
        return new PageInfo<>(foods);
    }

    public void delete(Integer id) {
        foodMapper.deleteByPrimaryKey(id);
    }

    public List<Food> getFoodBytepe(Integer type) {
        return foodMapper.selectList(type);
    }
}
