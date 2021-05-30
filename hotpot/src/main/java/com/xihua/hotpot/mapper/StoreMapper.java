package com.xihua.hotpot.mapper;

import com.xihua.hotpot.pojo.Store;
import org.apache.ibatis.annotations.Mapper;

/**
 * 火锅店铺Mapper
 */
@Mapper
public interface StoreMapper {

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
    int insert(Store record);

    /**
     * 根据ｉｄ　查询
     * @param id
     * @return
     */
    Store selectByPrimaryKey(Integer id);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Store record);
}