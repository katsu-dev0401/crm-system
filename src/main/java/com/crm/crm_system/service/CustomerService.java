package com.crm.crm_system.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.crm.crm_system.entity.Customer;
import com.crm.crm_system.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;

    // 顧客一覧を取得する
    public List<Customer> getCustomerList() {
        return customerMapper.selectAll();
    }
}