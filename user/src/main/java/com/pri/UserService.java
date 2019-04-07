package com.pri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 冻结资金
     * @param user
     * @return
     */
    @Transactional
    public Double tryPayment(User user){
        User newUser = userDao.save(user);
        return newUser.getBalance();
    }

    /**
     * 确认扣款
     * @param id
     * @return
     */
    @Transactional
    public Double confirmPayment(String id){
        User user = userDao.getOne(id);
        user.setStatus("1");
        User newUser = userDao.save(user);
        return newUser.getBalance();
    }

    /**
     * 解冻资金
     * @param id
     * @return
     */
    @Transactional
    public Double cancelPayment(String id){
        User user = userDao.getOne(id);
        user.setStatus("2");
        User newUser = userDao.save(user);
        return newUser.getBalance();
    }
}
