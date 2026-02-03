package com.crm.crm_system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.crm.crm_system.entity.Customer;

@Mapper
public interface CustomerMapper {
    // 全件検索メソッド（中身はXMLに書く）
    List<Customer> selectAll();
}