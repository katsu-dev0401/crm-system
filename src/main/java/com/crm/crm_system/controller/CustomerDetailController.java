package com.crm.crm_system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.crm.crm_system.entity.Contact;
import com.crm.crm_system.entity.Customer;
import com.crm.crm_system.mapper.UserMapper;
import com.crm.crm_system.service.CustomerService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerDetailController {

    private final CustomerService customerService;
    private final UserMapper userMapper;

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

    // 新規登録画面表示
    @GetMapping("/new")
    public String newForm(Model model) {
        Customer customer = new Customer();
        // 初期表示で1件分の枠を作るために空のContactを入れる
        customer.setContacts(new ArrayList<>());
        customer.getContacts().add(new Contact()); 
        
        model.addAttribute("customer", customer);
        model.addAttribute("users", userMapper.findAll());
        model.addAttribute("mode", "new");
        return "form";
    }

    // 編集画面表示
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        Customer customer = customerService.getCustomer(id);
        if (customer == null) {
            return "redirect:/";
        }
        // contactsが空なら空のContactを1つ追加
        if (customer.getContacts() == null || customer.getContacts().isEmpty()) {
            customer.getContacts().add(new Contact());
        }
        model.addAttribute("customer", customer);
        model.addAttribute("users", userMapper.findAll());
        model.addAttribute("mode", "edit");
        return "form";
    }

    // 保存処理
    @PostMapping("/save")
    public String save(
            @Validated Customer customer,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        
        // バリデーションエラーがある場合は入力画面に戻す
        if (bindingResult.hasErrors()) {
            model.addAttribute("customer", customer);
            model.addAttribute("users", userMapper.findAll());
            model.addAttribute("mode", customer.getCustomerId() == null ? "new" : "edit");
            return "form";
        }
        
        // 保存処理
        customerService.saveCustomer(customer);
        
        // 成功時は詳細画面（編集画面）へリダイレクト
        redirectAttributes.addFlashAttribute("message", "保存しました。");
        return "redirect:/customers/edit/" + customer.getCustomerId();
    }

    // 削除処理
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        customerService.deleteCustomer(id);
        redirectAttributes.addFlashAttribute("message", "削除しました");
        return "redirect:/";
    }
}




