package com.xihua.hotpot.mapper;

import com.xihua.hotpot.pojo.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface PictureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Picture record);

    Picture selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Picture record);

    List<Picture> getAll();

    @Select("select * from picture_index where valid = 1 order by sort ")
    List<Picture> selectValid();
}