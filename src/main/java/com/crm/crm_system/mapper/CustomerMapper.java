package com.crm.crm_system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.crm.crm_system.entity.Customer;
import com.crm.crm_system.form.CustomerSearchForm;

@Mapper
public interface CustomerMapper {
    // 全件取得
    List<Customer> selectAll();

    // 検索機能（CustomerSearchFormを使用）
    List<Customer> search(CustomerSearchForm searchForm);
    
    // ID指定で1件取得
    Customer selectById(@Param("id") Integer id);
    
    // 新規登録
    void insert(Customer customer);
    
    // 更新処理
    void update(Customer customer);
    
    // 論理削除
    void logicalDelete(@Param("customerId") Integer customerId);
}