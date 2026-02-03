package com.crm.crm_system.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.crm.crm_system.entity.Customer;
import com.crm.crm_system.service.CustomerService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // 「http://localhost:8080/」にアクセスした時の処理
    @GetMapping("/")
    public String index(Model model) {
        // DBからデータを取得
        List<Customer> customerList = customerService.getCustomerList();
        
        // 画面に渡す
        model.addAttribute("customers", customerList);
        
        // templates/list.html を表示する
        return "list";
    }
}