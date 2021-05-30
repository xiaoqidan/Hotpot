package com.xihua.hotpot.controller;

import com.xihua.hotpot.consts.Const;
import com.xihua.hotpot.pojo.Category;
import com.xihua.hotpot.pojo.Location;
import com.xihua.hotpot.repository.ResponseDo;
import com.xihua.hotpot.service.LocationService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 外卖地址
 * @author wufang
 */
@RestController
@RequestMapping(Const.hg + "/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    /**
     * 根据用户id 获取地址列表
     * @param userId
     * @return
     */
    @GetMapping
    public ResponseDo getLocation(@Param(value = "userId") Integer userId){
        return locationService.getLocation(userId);
    }

    /**
     * 根据地址id 获得单条地址的详细信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseDo getLocationById(@PathVariable(value = "id") Integer id){
        return locationService.getLocationById(id);
    }

    /**
     * 插入一条新的地址
     * @param location
     */
    @PostMapping
    public void insert(@RequestBody Location location){
        if(location.getId()!=null){
         locationService.update(location);
        }else {
            locationService.insert(location);
        }
    }

    /**
     * 更新默认地址
     * @param location
     */
    @PostMapping("/default")
    public void updateDefault(@RequestBody Location location){
        locationService.updateDefault(location);
    }

    /**
     * 删除地址
     * @param id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Integer id){
        locationService.delete(id);
    }
}
