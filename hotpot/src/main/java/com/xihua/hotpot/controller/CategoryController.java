package com.xihua.hotpot.controller;

import com.xihua.hotpot.consts.Const;
import com.xihua.hotpot.pojo.Category;
import com.xihua.hotpot.repository.ResponseDo;
import com.xihua.hotpot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单目录 对接前端url路径
 * RestController：表示它是一个controller成的类，返回的是json数据
 * RequestMapping：表示它的映射路径（与url进行匹配）
 * Autowired： 表示下面的这个类，会自动new XXX()，不用我们手动去new
 * @author wufang
 */
@RestController
@RequestMapping(Const.hg + "/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 获得目录的列表
     * @return
     */
    @GetMapping
    public List<Category> getCategory(Integer type){
        return categoryService.getCategory(type);
    }

    /**
     * 获得目录的列表，且列表里面包含了对应的菜品
     * @return
     */
    @GetMapping("/food")
    public List<Category> getCategoryAndFood(Integer type){
        return categoryService.getCategoryAndFood(type);
    }

    /**
     * 插入一个新的目录
     * @param category
     */
    @PostMapping
    public void insertCategory(@RequestBody Category category){
        categoryService.insertCategory(category);
    }

    /**
     * 根据id删除一个目录
     * @param id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Integer id){
        categoryService.delete(id);
    }

    /**
     * 更新目录是否显示
     * @param id
     */
    @PostMapping("/valid/{id}")
    public void updateVlid(@PathVariable(value = "id") Integer id){
        categoryService.updateVlid(id);
    }
}
