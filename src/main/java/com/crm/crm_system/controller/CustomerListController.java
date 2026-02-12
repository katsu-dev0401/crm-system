package com.crm.crm_system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.crm.crm_system.entity.Customer;
import com.crm.crm_system.form.CustomerSearchForm;
import com.crm.crm_system.service.CustomerService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CustomerListController {

    private final CustomerService customerService;

    // 業種マスタ
    @ModelAttribute("industryMap")
    public Map<String, String> getIndustryMap() {
        Map<String, String> industryMap = new HashMap<>();
        industryMap.put("IT", "IT業界");
        industryMap.put("REAL_ESTATE", "不動産");
        industryMap.put("RETAIL", "小売");
        industryMap.put("MANUFACTURING", "製造業");
        industryMap.put("FINANCE", "金融");
        industryMap.put("SERVICE", "サービス");
        industryMap.put("CONSTRUCTION", "建設");
        industryMap.put("FOOD", "飲食");
        return industryMap;
    }

    @GetMapping("/")
    public String index(
            @ModelAttribute CustomerSearchForm searchForm,
            Model model) {
        
        // 検索実行
        List<Customer> customerList = customerService.searchCustomers(searchForm);
        
        // 画面に渡す
        model.addAttribute("customers", customerList);
        model.addAttribute("searchForm", searchForm);
        
        return "list";
    }
}




