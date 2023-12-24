package com.mysql.smart.repository;

import com.mysql.smart.domain.SceneFurniture;
import com.mysql.smart.domain.Scenes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SceneFurnitureDao extends JpaRepository<SceneFurniture,Long> {
    List<SceneFurniture> findBySceneId(int id);

}
