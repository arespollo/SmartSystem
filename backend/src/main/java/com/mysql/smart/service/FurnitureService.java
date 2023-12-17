package com.mysql.smart.service;
import com.mysql.smart.domain.Furniture;
import com.mysql.smart.domain.ScheduledTask;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import java.util.List;

public interface FurnitureService  {

    Furniture addFurniture(Furniture furniture);
    Furniture delFurniture(Furniture furniture);
    Optional<Furniture> queryFurniture(int id);
    Furniture updateFurniture(Furniture updatedFurniture);
    List<ScheduledTask> getScheduledTasks(Long furnitureId);

    ScheduledTask scheduleTask(Long furnitureId, LocalDateTime startTime);

    void cancelScheduledTask(Long taskId);

    List<Furniture> queryFurniturnByUserId(int userid);
}