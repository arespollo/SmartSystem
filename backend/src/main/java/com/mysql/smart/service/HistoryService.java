package com.mysql.smart.service;

import com.mysql.smart.domain.History;

import java.util.List;

public interface HistoryService {

    History addHistory(History history);

    List<History> getHistoryByUserId(int userId);

    // 其他可能的业务方法
    // ...
}

