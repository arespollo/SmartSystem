package com.mysql.smart.service;

import com.mysql.smart.domain.SceneStatus;
import com.mysql.smart.repository.SceneStatusDao;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SceneStatusServicelmpl  implements SceneStatusService{
    @Resource
    private SceneStatusDao sceneStatusDao;
    @Override
    public List<SceneStatus> findAll() {
        return sceneStatusDao.findAll();
    }
    @Override
    public void updateSceneStatus(SceneStatus sceneStatus) {
        // 将所有的 SceneStatus 状态置零
        List<SceneStatus> allSceneStatus = sceneStatusDao.findAll();
        for (SceneStatus status : allSceneStatus) {
            status.setStatus(0);
            sceneStatusDao.save(status);
        }
        // 根据传入的信息开启对应的 Scene
        SceneStatus targetSceneStatus = sceneStatusDao.findById(sceneStatus.getId()).orElse(null);
        if (targetSceneStatus != null) {
            targetSceneStatus.setStatus(1);
            sceneStatusDao.save(targetSceneStatus);
        }

    }

}
