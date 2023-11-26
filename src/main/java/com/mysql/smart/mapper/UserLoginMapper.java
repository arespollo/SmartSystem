package com.mysql.smart.mapper;


import com.mysql.smart.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface UserLoginMapper extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    // Spring Data JPA会自动生成相应的查询实现
    // 大致等同于 SELECT * FROM users WHERE username = ?
}
