package com.pri;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private RemoteUserService remoteUserService;

    @Hmily(confirmMethod = "confirmOrder", cancelMethod = "cancelOrder")
    public String createOrder(Order order){
        orderDao.save(order);
        remoteUserService.tryPayment(order.getId());
        int i = 1/0;
        return order.getId();
    }

    public void confirmOrder(Order order){
        order.setStatus("1");
        orderDao.save(order);
        remoteUserService.confirmPayment(order.getId());
    }

    public void cancelOrder(Order order){
        order.setStatus("2");
        orderDao.save(order);
        remoteUserService.cancelPayment(order.getId());
    }

}
