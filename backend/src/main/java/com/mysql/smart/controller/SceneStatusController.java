package com.mysql.smart.controller;

import com.mysql.smart.domain.SceneStatus;
import com.mysql.smart.service.SceneStatusService;
import com.mysql.smart.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.mysql.smart.util.ErrorCode.*;
@RestController
@RequestMapping("/api/secure/scene")
public class SceneStatusController {
    @Autowired
    private SceneStatusService sceneStatusService;
    @PostMapping("/queryStatus")
    public Result<List<SceneStatus>> query(){
        List<SceneStatus> sceneStatusList = sceneStatusService.findAll();
        return Result.success(sceneStatusList, "查询成功！");
    }
    @PutMapping("/updateStatus")
    public Result<String> updateStatus(@RequestBody SceneStatus sceneStatus) {
        sceneStatusService.updateSceneStatus(sceneStatus);
        return Result.success("更新成功");
    }


}
