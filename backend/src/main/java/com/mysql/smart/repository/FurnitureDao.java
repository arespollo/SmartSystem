package com.mysql.smart.repository;


import com.mysql.smart.domain.Furniture;
import com.mysql.smart.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository

public interface FurnitureDao extends JpaRepository<Furniture,Long> {
    List<Furniture> findByUserId(long UserId);

}

