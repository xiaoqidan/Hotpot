package com.xihua.hotpot.mapper;

import com.xihua.hotpot.pojo.OrderContent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 操作订单内容表接口
 */
@Mapper
public interface OrderContentMapper {
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
    int insert(OrderContent record);

    /**
     * 根据id 查询
     * @param id
     * @return
     */
    OrderContent selectByPrimaryKey(Integer id);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(OrderContent record);

    /**
     * 根据订单id 查询
     * @param orderId
     * @return
     */
    List<OrderContent> selectByOrderId(Integer orderId);
}