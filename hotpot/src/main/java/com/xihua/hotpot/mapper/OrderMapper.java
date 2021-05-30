package com.xihua.hotpot.mapper;

import com.xihua.hotpot.dto.OrderQueryDto;
import com.xihua.hotpot.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作订单表接口
 */
@Mapper
public interface OrderMapper {

    List<Order> selectOrder(OrderQueryDto queryDto);
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
    int insert(Order record);

    /**
     * 根据id 查询
     * @param id
     * @return
     */
    Order selectByPrimaryKey(Integer id);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     * 获取用户的所有订单
     * @param userId
     * @return
     */
    @Select("select * from orders where user_id = #{userId}")
    List<Order> selectByUserId(Integer userId);

    @Select("select * from orders where YEAR(create_time)=YEAR(NOW())")
    List<Order> selectByYear();

    @Select(" select * from orders   where date_format(create_time,'%Y-%m')= #{date}")
    List<Order> seelctByMonth(String date);

    @Select(" SELECT LAST_INSERT_ID();")
    Integer getId();
}