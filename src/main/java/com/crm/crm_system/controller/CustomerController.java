package com.crm.crm_system.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam; // ★追加
import com.crm.crm_system.entity.Customer;
import com.crm.crm_system.service.CustomerService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/")
    public String index(
            // ★追加: URLパラメータを受け取る（例: /?name=テスト&rank=A）
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "rank", required = false) String rank,
            Model model) {
        
        // 検索実行
        List<Customer> customerList = customerService.getCustomerList(name, rank);
        
        // 画面に渡す
        model.addAttribute("customers", customerList);
        
        // 検索条件を画面に残すために渡す（検索フォームの初期値にする）
        model.addAttribute("searchName", name);
        model.addAttribute("searchRank", rank);
        
        return "list";
    }
}