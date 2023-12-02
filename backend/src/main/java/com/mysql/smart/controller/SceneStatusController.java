package com.mysql.smart.controller;

import com.mysql.smart.domain.SceneStatus;
import com.mysql.smart.service.SceneStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/scene")
public class SceneStatusController {
    @Autowired
    private SceneStatusService sceneStatusService;
    @PostMapping("/query-status")
    public Map<String,Object> query(@RequestBody SceneStatus scenestatus){
        List<SceneStatus> sceneStatusList = sceneStatusService.findAllByUserId(scenestatus.getUserId());
        Map<String, Object> response = new HashMap<>();
        response.put("code", "1");
        response.put("msg", "查询成功！");
        response.put("data", sceneStatusList);
        return response;
    }
    @PostMapping("/update-status")
    public Map<String, Object> updateStatus(@RequestBody SceneStatus sceneStatus) {
        sceneStatusService.updateSceneStatus(sceneStatus);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 1);
        response.put("msg", "更新成功");
        return response;
    }
}
