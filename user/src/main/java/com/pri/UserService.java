package com.pri;

import org.dromara.hmily.annotation.Hmily;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;

    /**
     * 冻结资金
     *
     * @param user
     * @return
     */
    @Hmily(confirmMethod = "confirmPayment", cancelMethod = "cancelPayment")
    public Double tryPayment(User user) {
        logger.info("冻结资金");
        User newUser = userDao.save(user);
        return newUser.getBalance();
    }

    /**
     * 确认扣款
     *
     * @param
     * @return
     */
    @Transactional
    public boolean confirmPayment(User user) {
        logger.info("确认冻结资金");
        user = userDao.getOne(user.getId());
        user.setStatus("1");
        userDao.save(user);
        int i = 1 / 0;
        return Boolean.TRUE;
    }

    /**
     * 解冻资金
     *
     * @param
     * @return
     */
    @Transactional
    public boolean cancelPayment(User user) {
        logger.info("取消冻结资金");
        user = userDao.getOne(user.getId());
        user.setStatus("2");
        userDao.save(user);
        return Boolean.TRUE;
    }
}
