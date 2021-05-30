package com.xihua.hotpot.service;

import com.xihua.hotpot.mapper.LocationMapper;
import com.xihua.hotpot.pojo.Location;
import com.xihua.hotpot.repository.ResponseDo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 外卖地址
 * @author wufang
 */
@Service
public class LocationService {
    @Resource
    private LocationMapper locationMapper;

    public ResponseDo getLocation(Integer userId){
        return new ResponseDo(locationMapper.selectByUserId(userId));
    }

    public void insert(Location location) {
        locationMapper.insert(location);
    }

    public ResponseDo getLocationById(Integer id) {
        return new ResponseDo(locationMapper.selectByPrimaryKey(id));
    }

    public void update(Location location) {
        locationMapper.updateByPrimaryKeySelective(location);
    }

    /**
     * 先清除所有的默认值，再重新赋值
     * @param location
     */
    @Transactional
    public void updateDefault(Location location) {
        locationMapper.clearDefault(location.getUserId());
        location.setValid((byte)1);
        locationMapper.updateByPrimaryKeySelective(location);

    }

    public void delete(Integer id) {
        locationMapper.deleteByPrimaryKey(id);
    }
}
