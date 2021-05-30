package com.xihua.hotpot.service;

import com.xihua.hotpot.consts.Const;
import com.xihua.hotpot.mapper.CategoryMapper;
import com.xihua.hotpot.mapper.FoodMapper;
import com.xihua.hotpot.pojo.Category;
import com.xihua.hotpot.repository.ResponseDo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单目录
 * @author wufang
 */
@Service
public class CategoryService {
    /**
     * 对应category表的处理接口
     */
    @Resource
    private CategoryMapper categoryMapper;
    /**
     * 对应food表的处理接口
     */
    @Resource
    private FoodMapper foodMapper;

    /**
     * 查询所有的目录，包括目录下的菜品
     * @return
     */
    public List<Category> getCategoryAndFood(Integer type){
        // 查询所有目录
        List<Category> categorys = categoryMapper.selectByStoreId(Const.StoreId,type);
        // 给每个目录赋值菜品
        for(Category category:categorys){
            category.setFoods(foodMapper.selectByCategoryId(category.getId()));
        }
        return categorys;
    }

    /**
     * 获取所有的目录
     * @return
     */
    public List<Category> getCategory(Integer type){
        // 查询所有目录
        List<Category> categorys = categoryMapper.selectByStoreIdAll(Const.StoreId,type);
        for(Category category:categorys){
            category.setNumber(foodMapper.selectByCategoryId(category.getId()).size());
        }
        return categorys;
    }

    /**
     * 插入一个新的目录
     * @param category
     */
    public void insertCategory(Category category) {
        //如果目录的id 是存在的，则直接更新数据表中的数据
        if(category.getId()!=null){
            categoryMapper.updateByPrimaryKeySelective(category);
        }else {
            // 数据不存在，在插入到数据表中
            category.setStoreId(1);
            categoryMapper.insert(category);
        }
    }

    /**
     * 删除 一个目录，保留剩下的菜品信息
     * @param id
     */
    public void delete(Integer id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新目录是否有效
     * @param id
     */
    public void updateVlid(Integer id) {
        categoryMapper.updateValid(id);
    }
}
