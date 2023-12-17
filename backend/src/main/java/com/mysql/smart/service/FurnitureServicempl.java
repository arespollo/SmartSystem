package com.mysql.smart.service;

import com.mysql.smart.domain.Furniture;
import com.mysql.smart.domain.ScheduledTask;
import com.mysql.smart.repository.FurnitureDao;
import com.mysql.smart.repository.ScheduledTaskDao;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FurnitureServicempl implements FurnitureService{

    @Resource
    private FurnitureDao furnitureDao;
    private final ScheduledTaskDao scheduledTaskDao;

    public FurnitureServicempl(FurnitureDao furnitureDao, ScheduledTaskDao scheduledTaskDao) {
        this.furnitureDao = furnitureDao;
        this.scheduledTaskDao = scheduledTaskDao;
    }

    @Override
    public Furniture addFurniture(Furniture furniture) {
        // 执行家具添加的逻辑，比如保存到数据库或执行其他操作
        // 这里仅作示例，打印家具信息
        System.out.println("添加家具：" + furniture.getId());
        furnitureDao.save(furniture);
        return furniture;
    }

    @Override
    public Furniture delFurniture(Furniture furniture) {
        furnitureDao.delete(furniture);
        //System.out.println("删除家具：" + furniture.getId()+furniture.getLocation()+furniture.getLocation());
        return furniture;
    }

    @Override
    public Optional<Furniture> queryFurniture(int id) {
        if(furnitureDao.findById((long) id).isPresent()){
            return furnitureDao.findById((long) id);
        }
        else{
            return null;
        }
    }



    @Override
    public Furniture updateFurniture(Furniture updatedFurniture) {
        // 根据ID获取要更新的家具
        Furniture existingFurniture = furnitureDao.findById((long)updatedFurniture.getId()).orElse(null);

        if (existingFurniture != null) {

            BeanUtils.copyProperties(updatedFurniture, existingFurniture, getNullPropertyNames(updatedFurniture));

            return furnitureDao.save(existingFurniture);
        } else {
            // 如果找不到要更新的家具，可以抛出异常或返回null，具体根据需求而定
            return null;
        }
    }
    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null || StringUtils.isEmpty(srcValue.toString())) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }


    @Override
    public List<ScheduledTask> getScheduledTasks(Long furnitureId) {
        return scheduledTaskDao.findByFurnitureId(furnitureId);
    }



    @Override
    public ScheduledTask scheduleTask(Long furnitureId, LocalDateTime startTime) {
        Furniture furniture = furnitureDao.findById(furnitureId).orElse(null);

        if (furniture != null) {
            ScheduledTask task = new ScheduledTask();
            task.setFurniture(furniture);
            task.setStartTime(startTime);
            task.setActive(true);
            return scheduledTaskDao.save(task);
        } else {
            return null;
        }
    }

    @Override
    public void cancelScheduledTask(Long taskId) {
        scheduledTaskDao.deleteById(taskId);
    }

    @Override
    public List<Furniture> queryFurniturnByUserId(int userid) {
        return furnitureDao.findByUserId(userid);
    }
    /*@Override
    public User querySceneStatus(long id){
        User user=userDao.findById(id).orElse(null);
        if(user!=null){
            user.setPassword("");
            return user;
        }
        return null;
    }*/


}
