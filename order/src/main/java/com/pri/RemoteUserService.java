package com.pri;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface RemoteUserService {

    @GetMapping("/user/tryPayment/{id}")
    String tryPayment(@PathVariable String id);

    @GetMapping("/user/confirmPayment/{id}")
    void confirmPayment(@PathVariable String id);

    @GetMapping("/user/cancelPayment/{id}")
    void cancelPayment(@PathVariable String id);

}
