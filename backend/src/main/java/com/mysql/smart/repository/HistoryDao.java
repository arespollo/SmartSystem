package com.mysql.smart.repository;
import com.mysql.smart.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryDao extends JpaRepository<History, Integer> {

    List<History> findAllByUserId(int userId);

    // 其他可能的查询方法
    // ...
}
