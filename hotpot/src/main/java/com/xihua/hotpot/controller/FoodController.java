package com.xihua.hotpot.controller;

import com.xihua.hotpot.consts.Const;
import com.xihua.hotpot.dto.FoodQueryDto;
import com.xihua.hotpot.pojo.Food;
import com.xihua.hotpot.repository.ResponseDo;
import com.xihua.hotpot.service.FoodService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 菜品
 * @author wufang
 */
@RestController
@RequestMapping(Const.hg + "/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    /**
     * 根据目录id 获得目录下的菜品
     * @param categoryId
     * @return
     */
    @GetMapping("/categoryId")
    public ResponseDo getFood(@PathVariable Integer categoryId){
        return foodService.getFood(categoryId);
    }

    @GetMapping("/list")
    public ResponseDo getFoods(FoodQueryDto queryDto){
        return new ResponseDo(foodService.getFoods(queryDto));
    }

    /**
     * 获取推荐菜品
     * 根据销量，推荐销量前五的菜品到前端展示
     * @return
     */
    @GetMapping("/tj")
    public ResponseDo getTjFood(){
        return foodService.getTjFood();
    }

    /**
     * 根据id 获得菜品的详细信息
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public ResponseDo getFoodById(@PathVariable(value = "id") Integer id){
        return foodService.getFoodById(id);
    }

    /**
     * 根据id 删除菜品
     * @param id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Integer id){
        foodService.delete(id);
    }

    /**
     * 添加新的菜品
     * @param food
     */
    @PostMapping
    public void insert(@RequestBody Food food){
        foodService.insert(food);
    }
}
