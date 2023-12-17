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
    /*List<Furniture> furnitureList = null;
    //Furniture update(Furniture furniture);
    public default Furniture update(Furniture updatedFurniture) {
        for (Furniture furniture : furnitureList) {
            if (Objects.equals(furniture.getId(), updatedFurniture.getId())) {
                // 更新家具的属性
                furniture.setType(updatedFurniture.getType());
                furniture.setLocation(updatedFurniture.getLocation());
                // 根据需要更新其他属性

                // 返回更新后的家具
                return furniture;
            }
        }

        // 如果找不到具有给定ID的家具，返回null或引发异常
        return null;
    }*/

}

