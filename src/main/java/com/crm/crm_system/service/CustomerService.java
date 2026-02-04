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

    // ★修正: 検索条件を受け取れるようにする
    public List<Customer> getCustomerList(String name, String rank) {
        // 名前もランクも空なら、全件検索（selectAll）でもいいが、
        // searchメソッドの動的SQLが優秀なので、searchに任せてもOK。
        // ここでは明示的に使い分けてみる。
        
        if ((name == null || name.isEmpty()) && (rank == null || rank.isEmpty())) {
            return customerMapper.selectAll();
        } else {
            return customerMapper.search(name, rank);
        }
    }
}