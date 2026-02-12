package com.crm.crm_system.mapper;

import com.crm.crm_system.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT user_id AS userId, user_name AS userName FROM m_user ORDER BY user_id")
    List<User> findAll();
}

