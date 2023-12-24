package com.mysql.smart.repository;

import com.mysql.smart.domain.SceneFurniture;
import com.mysql.smart.domain.Scenes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SceneFurnitureDao extends JpaRepository<SceneFurniture,Long> {
    List<SceneFurniture> findBySceneid(int id);

}
