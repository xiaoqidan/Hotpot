package com.xihua.hotpot.controller;

import com.xihua.hotpot.consts.Const;
import com.xihua.hotpot.dto.OrderQueryDto;
import com.xihua.hotpot.pojo.Order;
import com.xihua.hotpot.repository.ResponseDo;
import com.xihua.hotpot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单
 * @author wufang
 */
@RestController
@RequestMapping(Const.hg + "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 获取订单
     * @return
     */
    @GetMapping
    public ResponseDo getOrder(@RequestParam Integer userId){
        return orderService.getOrderList(userId);
    }


    @GetMapping("/list")
    public ResponseDo orderList(OrderQueryDto queryDto) {
        return new ResponseDo(orderService.getOrderList(queryDto));
    }

    @GetMapping("/year")
    public ResponseDo getListByYear(){
        return new ResponseDo(orderService.selectByYear());
    }
    @GetMapping("/month")
    public ResponseDo getListByMonth(String date){
        return new ResponseDo(orderService.getListByMonth(date));
    }
    /**
     * 获取订单详情
     * @return
     */
    @GetMapping("/detail")
    public Order getOrderDetail(Integer orderId){
        return orderService.getOrderDetail(orderId);
    }

    /**
     * 创建订单
     * @param order
     */
    @PostMapping("/pay")
    public void createOrder(@RequestBody Order order){
        orderService.createOrder(order);
    }


    @PostMapping("/update")
    public void updateOrder(@RequestBody Order order){
        orderService.updateOrder(order);
    }


}
