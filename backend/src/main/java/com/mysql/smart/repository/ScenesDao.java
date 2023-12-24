package com.mysql.smart.repository;

import com.mysql.smart.domain.Furniture;
import com.mysql.smart.domain.Scenes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ScenesDao extends JpaRepository<Scenes,Long> {
    List<Scenes> findByUserId(long UserId);
}
