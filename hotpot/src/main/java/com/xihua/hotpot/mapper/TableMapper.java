package com.xihua.hotpot.mapper;

import com.xihua.hotpot.pojo.Table;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Table record);

    int insertSelective(Table record);

    Table selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Table record);

    int updateByPrimaryKey(Table record);

    List<Table> selectAll();
}