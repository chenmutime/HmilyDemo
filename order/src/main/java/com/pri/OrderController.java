package com.pri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create/{id}")
    public String createOrder(@PathVariable String id){
        Order order = new Order();
        order.setAmount(22.0);
        order.setId(id);
        order.setName("phone");
        order.setStatus("0");
        orderService.createOrder(order);
        return order.getId();
    }
}
