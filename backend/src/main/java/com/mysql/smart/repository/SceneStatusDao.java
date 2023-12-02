package com.mysql.smart.repository;


import com.mysql.smart.domain.SceneStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SceneStatusDao extends JpaRepository<SceneStatus,Long> {
    List<SceneStatus> findAllByUserId(int userId);
    SceneStatus findByUserIdAndScene(int userId, String scene);

}
