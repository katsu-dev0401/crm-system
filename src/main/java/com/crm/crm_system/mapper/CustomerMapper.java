package com.crm.crm_system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param; // ★追加
import com.crm.crm_system.entity.Customer;

@Mapper
public interface CustomerMapper {
    // 全件取得（既存）
    List<Customer> selectAll();

    // ★追加: 検索機能
    // @Paramを使うと、XML側で #{name}, #{rank} という名前で変数が使えるようになります
    List<Customer> search(@Param("name") String name, @Param("rank") String rank);
}