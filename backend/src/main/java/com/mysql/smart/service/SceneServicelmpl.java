package com.mysql.smart.service;

import com.mysql.smart.domain.Furniture;
import com.mysql.smart.domain.SceneFurniture;
import com.mysql.smart.domain.Scenes;
import com.mysql.smart.repository.FurnitureDao;
import com.mysql.smart.repository.SceneFurnitureDao;
import com.mysql.smart.repository.ScenesDao;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SceneServicelmpl implements SceneService{
    @Resource
    private ScenesDao scenesDao;
    @Resource
    private SceneFurnitureDao sceneFurnitureDao;
    @Resource
    private FurnitureDao furnitureDao;
    @Override
    public Scenes addScene(Scenes scenes) {
        scenesDao.save(scenes);
        return scenes;
    }

    @Override
    public Scenes delScene(Scenes scenes) {
        scenesDao.delete(scenes);
        return null;
    }

    @Override
    public SceneFurniture addSF(SceneFurniture sceneFurniture) {
        sceneFurnitureDao.save(sceneFurniture);
        return sceneFurniture;
    }

    @Override
    public SceneFurniture delSF(SceneFurniture sceneFurniture) {
        sceneFurnitureDao.delete(sceneFurniture);
        return null;
    }

    @Override
    public Optional<Scenes> findByid(int id) {
        return scenesDao.findById((long)id);
    }

    @Override
    public Scenes updateSceneStatus(int sceneId, int newStatus) {
        Optional<Scenes> optionalScene = scenesDao.findById((long) sceneId);
        if (optionalScene.isPresent()&&newStatus!=0) {
            Scenes scene = optionalScene.get();
            scene.setStatus(newStatus);

            // 获取与该场景关联的所有SceneFurniture
            List<SceneFurniture> sceneFurnitureList = scene.getSceneFurnitureList();

            // 更新相关的Furniture实体的status
            for (SceneFurniture sceneFurniture : sceneFurnitureList) {
                Optional<Furniture> optionalFurniture = furnitureDao.findById((long) sceneFurniture.getFurid());
                if (optionalFurniture.isPresent()) {
                    Furniture furniture = optionalFurniture.get();
                    furniture.setStatus(sceneFurniture.getFurstatus());
                    furnitureDao.save(furniture);
                }
            }

            // 保存更新后的Scene实体
            scenesDao.save(scene);
            return scene;
        }
        return null;
    }


}