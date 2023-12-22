package com.mysql.smart.repository;
import com.mysql.smart.domain.FurnitureHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FurnitureHistoryDao extends JpaRepository<FurnitureHistory, Integer> {

    List<FurnitureHistory> findAllByUserId(int userId);

    // 其他可能的查询方法
    // ...
}
