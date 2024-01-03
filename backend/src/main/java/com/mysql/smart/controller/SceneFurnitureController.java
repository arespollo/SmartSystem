package com.mysql.smart.controller;

import com.mysql.smart.domain.SceneFurniture;
import com.mysql.smart.service.SceneService;
import com.mysql.smart.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/secure/scene")
public class SceneFurnitureController {
    @Autowired
    private SceneService sceneService;
    @PostMapping("/addSceneFurniture")
    public Result<SceneFurniture> addSF(@RequestBody SceneFurniture sceneFurniture){
        SceneFurniture addedSceneFurniture=sceneService.addSF(sceneFurniture);
        if (addedSceneFurniture != null) {
            return Result.success(addedSceneFurniture, "添加成功！");
        } else {
            return Result.error("103", "添加失败！");
        }
    }
    @PostMapping("/delSceneFurniture")
    public Result<String> delSceneFurniture(@RequestBody SceneFurniture sceneFurniture) {
        SceneFurniture deletedsceneFurniture=sceneService.delSF(sceneFurniture);
        if (deletedsceneFurniture==null) {
            return Result.success("删除成功！");
        } else {
            return Result.error("104", "删除失败！");
        }
    }

    @PostMapping("/updateSceneFurniture")
    public Result<SceneFurniture> updateSceneFurniture(@RequestBody SceneFurniture sceneFurniture) {
        SceneFurniture updatedSceneFurniture=sceneService.updateSF(sceneFurniture);
        if (updatedSceneFurniture!=null) {
            return Result.success(updatedSceneFurniture);
        } else {
            return Result.error("105", "更新失败！");
        }
    }
}
