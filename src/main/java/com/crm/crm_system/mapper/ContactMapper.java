package com.crm.crm_system.mapper;

import com.crm.crm_system.entity.Contact;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ContactMapper {
    void insert(Contact contact);
    void deleteByCustomerId(Integer customerId);
    List<Contact> selectByCustomerId(Integer customerId);
}

