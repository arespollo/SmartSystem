package com.mysql.smart.controller;

import com.mysql.smart.domain.Scenes;
import com.mysql.smart.service.SceneService;
import com.mysql.smart.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/scene")
public class SceneController {
    @Autowired
    private SceneService sceneService;

    @PostMapping("/addScene")
    public Result<Scenes> addScene(@RequestBody Scenes scenes) {
        Scenes addedScene = sceneService.addScene(scenes);
        if (addedScene != null) {
            return Result.success(addedScene, "添加场景成功！");
        } else {
            return Result.error("101", "添加场景失败！");
        }
    }

    @PostMapping("/delScene")
    public Result<Scenes> delScene(@RequestBody Scenes scenes) {
        Scenes deletedScene = sceneService.delScene(scenes);
        if (deletedScene == null) {
            return Result.success(deletedScene, "删除场景成功！");
        } else {
            return Result.error("102", "删除场景失败！");
        }
    }
    @PostMapping("/getScene")
    public Result<Optional<Scenes>> getScene(@RequestBody Scenes scenes) {
        int id=scenes.getId();
        Optional<Scenes> scene = sceneService.findByid(id);
        if (scene != null) {
            return Result.success(scene, "获取场景成功！");
        } else {
            return Result.error("103", "获取场景失败！");
        }
    }

    @PostMapping("/updateScene")
    public Result<Scenes> updateScene(@RequestBody Scenes scenes) {
        int id=scenes.getId();
        int status=scenes.getStatus();
        Scenes updatedScene = sceneService.updateSceneStatus(id,status);
        if (updatedScene != null) {
            return Result.success(updatedScene, "更新场景成功！");
        } else {
            return Result.error("104", "更新场景失败！");
        }
    }
}
