package com.mysql.smart.controller;

import com.mysql.smart.domain.Furniture;

import com.mysql.smart.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.mysql.smart.service.FurnitureService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mysql.smart.util.ErrorCode.*;
@RestController
@RequestMapping("/api/secure/furniture")
public class FurnitureController {

    @Autowired
    private FurnitureService furnitureService;
    @PostMapping("/addFur")
    public Result<Furniture> addFurniture(@RequestBody Furniture furniture) {
        Furniture fur = furnitureService.addFurniture(furniture);
        if (fur != null) {
            return Result.success(fur, "增加成功！");
        } else {
            return Result.error(ADDFUR_ERROR);
        }
    }

    @PostMapping("/delFur")
    public Result<Furniture> delFurniture(@RequestBody Furniture furniture) {
        Furniture fur = furnitureService.delFurniture(furniture);
        if (fur != null) {
            return Result.success(fur, "删除成功！");
        } else {
            return Result.error(DELFUR_ERROR);
        }
    }

}
