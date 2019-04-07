package com.pri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/tryPayment/{id}")
    public String tryPayment(@PathVariable String id){
        User user = new User();
        user.setId(id);
        user.setName("hmm");
        user.setBalance(22.0);
        user.setStatus("0");
        userService.tryPayment(user);
        return "" + id;
    }

    @GetMapping("/confirmPayment/{id}")
    public String confirmPayment(@PathVariable String id){
        userService.confirmPayment(id);
        return "" + id;
    }

    @GetMapping("/cancelPayment/{id}")
    public String cancelPayment(@PathVariable String id){
        userService.cancelPayment(id);
        return "" + id;
    }
}
