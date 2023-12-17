package com.mysql.smart.repository;

import com.mysql.smart.domain.Furniture;
import com.mysql.smart.domain.Scenes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScenesDao extends JpaRepository<Scenes,Long> {

}
