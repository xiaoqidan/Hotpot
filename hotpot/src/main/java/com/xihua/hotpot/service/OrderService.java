package com.xihua.hotpot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.xihua.hotpot.dto.OrderQueryDto;
import com.xihua.hotpot.mapper.FoodMapper;
import com.xihua.hotpot.mapper.LocationMapper;
import com.xihua.hotpot.mapper.OrderContentMapper;
import com.xihua.hotpot.mapper.OrderMapper;
import com.xihua.hotpot.pojo.Food;
import com.xihua.hotpot.pojo.Order;
import com.xihua.hotpot.pojo.OrderContent;
import com.xihua.hotpot.repository.ResponseDo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 订单
 * @author wufang
 */
@Service
public class OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderContentMapper orderContentMapper;
    @Resource
    private FoodMapper foodMapper;
    @Resource
    private LocationMapper locationMapper;

    public ResponseDo getOrder(){
        return new ResponseDo(orderMapper.selectByPrimaryKey(1));
    }

    /**
     * 创建订单
     * @param order
     */
    @Transactional
    public void createOrder(Order order) {
        //设置订单创建时间
        order.setCreateTime(new Date());
        orderMapper.insert(order);
        // TODO 并发操作存在问题
        int id = orderMapper.getId();
        //遍历订单内容
        for(OrderContent orderContent: order.getFoods()){
            orderContent.setOrderId(id);
            orderContent.setFoodId(orderContent.getId());
            orderContent.setId(null);
            //订单内容入库
            orderContentMapper.insert(orderContent);
            // 菜品销量+1
            foodMapper.addNumber(orderContent.getFoodId());
        }
    }

    /**
     * 获取单个订单的详细信息
     * @param orderId
     * @return
     */
    public Order getOrderDetail(Integer orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        List<OrderContent> foods = orderContentMapper.selectByOrderId(orderId);
        order.setFoods(foods);
        return order;
    }

    /**
     * 获取用户的所有订单
     * @param userId
     * @return
     */
    public ResponseDo getOrderList(Integer userId) {
        List<Order> orders = orderMapper.selectByUserId(userId);
        deal(orders);
        return new ResponseDo(orders);
    }

    public PageInfo<Order> getOrderList(OrderQueryDto queryDto) {
        int page=queryDto.getOffset() / queryDto.getLimit()+1;
        PageHelper.startPage(page, queryDto.getLimit());
        List<Order> orders = orderMapper.selectOrder(queryDto);
        deal(orders);
        PageInfo orderPageInfo = new PageInfo<>(orders);
        return orderPageInfo;
    }

    public List<Order> getOrderListAll(Integer type ) {
        OrderQueryDto queryDto = new OrderQueryDto();
        queryDto.setType(type);
        List<Order> orders = orderMapper.selectOrder(queryDto);
        deal(orders);
        return orders;
    }

    private void deal(List<Order> orders){
        if(orders!=null){
            StringBuilder content = null;
            //拼接生成订单的内容（将所有菜品加在一起）
            for(Order order:orders){
                float sum = 0 ;
                content = new StringBuilder("");
                List<OrderContent> contents = orderContentMapper.selectByOrderId(order.getId());
                if(!CollectionUtils.isEmpty(contents)) {
                    for (OrderContent food : contents) {
                        content.append(food.getName() + "x" + food.getNumber().toString() + "  ");
                        sum = sum + (food.getPrice()*food.getNumber());
                    }
                    order.setContent(String.valueOf(content));
                }
                order.setMoney(sum);
                if(order.getType() == 2){
                    order.setLocation(locationMapper.selectByPrimaryKey(order.getLocationId()));
                }
            }
        }
    }

    public void updateOrder(Order order) {
        if(order.getId() == null){
            System.out.println("更新订单，id不能为空");
            return;
        }
        orderMapper.updateByPrimaryKeySelective(order);
    }

    public List<Order> selectByYear(){
        return orderMapper.selectByYear();
    }

    public List<Order> getListByMonth(String date) {
        return orderMapper.seelctByMonth(date);
    }

}
