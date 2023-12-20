package com.mysql.smart.service;

import com.mysql.smart.domain.Furniture;
import com.mysql.smart.domain.ScheduledTask;
import com.mysql.smart.repository.FurnitureDao;
import com.mysql.smart.service.FurnitureService;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class FurnitureServiceTest {
    @Autowired
    private FurnitureDao repository;
    @Resource
    private FurnitureService service;

    @Test
    void addFurniture() {
        Furniture furniture = new Furniture("TV",1, "My TV",  32);
        Furniture savedFurniture = service.addFurniture(furniture);
        assertNotNull(savedFurniture);
        // Perform additional assertions as necessary
    }

    @Test
    void delFurniture() {
        Furniture furniture = new Furniture("TV",1, "My TV",  32);
        assertDoesNotThrow(() -> service.delFurniture(furniture));
    }

    @Test
    void queryFurniture() {
        int id = 43;
        Optional<Furniture> expectedFurniture = Optional.of(new Furniture()); // Set necessary fields

        Optional<Furniture> result = service.queryFurniture(id);
        assertTrue(result.isPresent());
        // Additional assertions
    }

    @Test
    void updateFurniture() {
        Furniture updatedFurniture = new Furniture("TV",1, "My TV",  31);
        updatedFurniture.setId(43);
        Furniture result = service.updateFurniture(updatedFurniture);
        assertNotNull(result);
        // Additional assertions
    }

    @Test
    void queryFurnitureByUserId() {
        int userId = 32;
        List<Furniture> result = service.queryFurniturByUserId(userId);
        assertFalse(result.isEmpty());
        // Additional assertions
    }

}
