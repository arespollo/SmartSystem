package com.mysql.smart.service;

import com.mysql.smart.domain.SceneFurniture;
import com.mysql.smart.domain.Scenes;

import java.util.Optional;

public interface SceneService {
    Scenes addScene(Scenes scenes);

    Scenes delScene(Scenes scenes);

    SceneFurniture addSF(SceneFurniture sceneFurniture);

    SceneFurniture delSF(SceneFurniture sceneFurniture);

    Optional<Scenes> findByid(int id);

    Scenes updateSceneStatus(int sceneId, int newStatus);
}
