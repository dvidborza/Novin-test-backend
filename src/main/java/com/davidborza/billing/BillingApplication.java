package com.davidborza.billing;

import com.davidborza.billing.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BillingApplication {

    @Autowired
    private RoleServiceImpl roleService;

    public static void main(final String[] args) {
        SpringApplication.run(BillingApplication.class, args);
    }
}
