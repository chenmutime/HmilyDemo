package com.pri;

import org.dromara.hmily.annotation.Hmily;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private RemoteUserService remoteUserService;

    @Hmily(confirmMethod = "confirmOrder", cancelMethod = "cancelOrder")
    public String createOrder(Order order){
        orderDao.save(order);
        remoteUserService.tryPayment(order.getId());
        logger.info("创建订单");
        return order.getId();
    }

    @Transactional
    public void confirmOrder(Order order){
        order.setStatus("1");
        orderDao.save(order);
        logger.info("确认订单");
    }

    @Transactional
    public void cancelOrder(Order order){
        order.setStatus("2");
        orderDao.save(order);
        logger.info("取消订单");
    }

}
