package com.mysql.smart.controller;

import com.mysql.smart.domain.History;
import com.mysql.smart.service.HistoryService;
import com.mysql.smart.util.ErrorCode;
import com.mysql.smart.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/secure/history")
public class Historycontroller {

    @Autowired
    private HistoryService historyService;

    @PostMapping("/queryHistoryByUserId")
    public Result<List<History>> queryHistoryByUserId(@RequestAttribute("id") Integer userId) {
        if (userId == null) {
            return Result.error(ErrorCode.valueOf("用户ID未找到。"));
        }

        List<History> historyList = historyService.getHistoryByUserId(userId);
        if (!historyList.isEmpty()) {
            return Result.success(historyList, "查询成功！");
        } else {
            return Result.error(ErrorCode.valueOf("未找到该用户的历史记录。"));
        }
    }
}

