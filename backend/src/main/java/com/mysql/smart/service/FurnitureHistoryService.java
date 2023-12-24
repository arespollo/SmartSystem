package com.mysql.smart.service;

import com.mysql.smart.domain.FurnitureHistory;

import java.util.List;

import java.util.List;

public interface FurnitureHistoryService {

    FurnitureHistory addHistory(FurnitureHistory history);

    List<FurnitureHistory> getHistoryByUserId(int userId);

    // 其他可能的业务方法
    // ...
}

