package com.mysql.smart.service;

import com.mysql.smart.domain.FurnitureHistory;
import com.mysql.smart.repository.FurnitureHistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FurnitureHistoryServiceImpl implements FurnitureHistoryService {

    @Autowired
    private FurnitureHistoryDao historyDao;

    @Override
    public FurnitureHistory addHistory(FurnitureHistory history) {
        history.setDate(LocalDateTime.now());
        return historyDao.save(history);
    }

    @Override
    public List<FurnitureHistory> getHistoryByUserId(int userId) {
        // 根据用户ID查询历史记录
        return historyDao.findAllByUserId(userId);
    }

    // 其他可能的业务方法的具体实现
    // ...
}
