package com.crm.crm_system.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.crm.crm_system.entity.Contact;
import com.crm.crm_system.entity.Customer;
import com.crm.crm_system.form.CustomerSearchForm;
import com.crm.crm_system.mapper.ContactMapper;
import com.crm.crm_system.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final ContactMapper contactMapper;

    // 検索処理（CustomerSearchFormを使用）
    public List<Customer> searchCustomers(CustomerSearchForm searchForm) {
        // 検索条件が全て空の場合は全件取得
        boolean hasSearchCondition = 
            (searchForm.getCompanyName() != null && !searchForm.getCompanyName().isEmpty()) ||
            (searchForm.getCompanyNameKana() != null && !searchForm.getCompanyNameKana().isEmpty()) ||
            (searchForm.getIndustryCode() != null && !searchForm.getIndustryCode().isEmpty()) ||
            searchForm.getDateFrom() != null ||
            searchForm.getDateTo() != null ||
            searchForm.getIsEmailAllowed() != null;
        
        if (hasSearchCondition) {
            return customerMapper.search(searchForm);
        } else {
            return customerMapper.selectAll();
        }
    }
    
    // ID指定で1件取得
    public Customer getCustomer(Integer id) {
        return customerMapper.selectById(id);
    }
    
    // 保存処理（IDがnullならinsert、あればupdate）
    // Contactも同時に保存（全削除→全登録の戦略）
    public void saveCustomer(Customer customer) {
        // 1. 親(Customer)の保存
        if (customer.getCustomerId() == null) {
            customerMapper.insert(customer);
        } else {
            customerMapper.update(customer);
            contactMapper.deleteByCustomerId(customer.getCustomerId()); // 更新時は子をリセット
        }

        // 2. 子(Contact)の保存
        if (customer.getContacts() != null) {
            for (Contact contact : customer.getContacts()) {
                if (contact.getContactName() != null && !contact.getContactName().trim().isEmpty()) {
                    contact.setCustomerId(customer.getCustomerId()); // 親IDをセット
                    contactMapper.insert(contact);
                }
            }
        }
    }
    
    // 論理削除処理
    public void deleteCustomer(Integer id) {
        customerMapper.logicalDelete(id);
    }
}