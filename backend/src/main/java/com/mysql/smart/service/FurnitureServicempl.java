package com.mysql.smart.service;

import com.mysql.smart.domain.Furniture;
import com.mysql.smart.repository.FurnitureDao;
import com.mysql.smart.repository.SceneStatusDao;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class FurnitureServicempl implements FurnitureService{

    @Resource
    private FurnitureDao furnitureDao;
    @Override
    public Furniture addFurniture(Furniture furniture) {
        // 执行家具添加的逻辑，比如保存到数据库或执行其他操作
        // 这里仅作示例，打印家具信息
        System.out.println("添加家具：" + furniture.getId()+furniture.getLocation()+furniture.getLocation());
        furnitureDao.save(furniture);

        return furniture;
    }

    @Override
    public Furniture delFurniture(Furniture furniture) {
        System.out.println("删除家具：" + furniture.getId()+furniture.getLocation()+furniture.getLocation());
        return furniture;
    }

    @Override
    public Furniture queryFurniture(Furniture furniture) {
        System.out.println("查找到家具：" + furniture.getId()+furniture.getLocation()+furniture.getLocation());
        return furniture;
    }


}
