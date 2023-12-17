package com.mysql.smart.repository;

import com.mysql.smart.domain.ScheduledTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduledTaskDao extends JpaRepository<ScheduledTask, Long> {
    List<ScheduledTask> findByFurnitureId(Long furnitureId);
    // 可根据业务需要添加自定义查询方法
}