package com.xihua.hotpot.service;

import com.xihua.hotpot.mapper.PictureMapper;
import com.xihua.hotpot.pojo.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hfy
 * @date 2021/5/27 22:34
 */
@Service
public class PictureService {
    @Resource
    private PictureMapper pictureMapper;

    public List<Picture> getAll() {
        return pictureMapper.getAll();
    }

    public void insertOrUpdate(Picture picture) {
        if(picture.getId() != null){
            pictureMapper.updateByPrimaryKeySelective(picture);
        }else{
            pictureMapper.insert(picture);
        }
    }

    public void deleteById(Integer id) {
        pictureMapper.deleteByPrimaryKey(id);
    }

    public List<Picture> getList() {
        return pictureMapper.selectValid();
    }
}
