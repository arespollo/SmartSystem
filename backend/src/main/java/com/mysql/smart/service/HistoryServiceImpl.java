package com.mysql.smart.service;

import com.mysql.smart.domain.History;
import com.mysql.smart.repository.HistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryDao historyDao;

    @Override
    public History addHistory(History history) {
        history.setDate(LocalDateTime.now());
        return historyDao.save(history);
    }

    @Override
    public List<History> getHistoryByUserId(int userId) {
        // 根据用户ID查询历史记录
        return historyDao.findAllByUserId(userId);
    }

    // 其他可能的业务方法的具体实现
    // ...
}
