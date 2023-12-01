package com.mysql.smart.repository;


import com.mysql.smart.domain.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FurnitureDao extends JpaRepository<Furniture,Long> {



}
