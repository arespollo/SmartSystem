package com.mysql.smart.service;

import com.mysql.smart.domain.SceneStatus;

import java.util.List;

public interface SceneStatusService{
    List<SceneStatus> findAllByUserId(int userId);

    void updateSceneStatus(SceneStatus sceneStatus);

}
