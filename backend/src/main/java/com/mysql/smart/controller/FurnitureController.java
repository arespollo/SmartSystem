package com.mysql.smart.controller;

import com.mysql.smart.domain.Furniture;

import com.mysql.smart.service.FurnitureService;
import com.mysql.smart.util.ErrorCode;
import com.mysql.smart.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.mysql.smart.util.ErrorCode.*;
@RestController
@RequestMapping("/api/secure/furniture")
public class FurnitureController {
    @Autowired
    private FurnitureService furnitureService;
    @PostMapping("/addFur")
    public Result<Furniture> addFurniture(@RequestBody Furniture furniture, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 处理数据校验错误
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            String errorMessage = generateErrorMessage(fieldErrors);
            return Result.error(ErrorCode.valueOf(errorMessage));
        }

        Furniture fur = furnitureService.addFurniture(furniture);
        if (fur != null) {
            return Result.success(fur, "增加成功！");
        } else {
            return Result.error(ADDFUR_ERROR);
        }
    }

    @PostMapping("/delFur")
    public Result<Furniture> delFurniture(@RequestBody Furniture furniture, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 处理数据校验错误
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            String errorMessage = generateErrorMessage(fieldErrors);
            return Result.error(valueOf(errorMessage));
        }
        Furniture fur = furnitureService.delFurniture(furniture);
        if (fur != null) {
            return Result.success(fur, "删除成功！");
        } else {
            return Result.error(DELFUR_ERROR);
        }
    }

    @PostMapping("/queryFur")
    public Result queryFurniture(@RequestBody Map<String, Integer> requestBody) {
        int id = requestBody.get("id");
        Optional<Furniture> fur = furnitureService.queryFurniture(id);
        if (fur != null) {
            return Result.success(fur, "查找成功！");
        } else {
            return Result.error(QUERY_ERROR);
        }
    }

    @PostMapping("/queryFurnitureByUserId")
    public Result<List<Furniture>> queryFurnitureByUserId(@RequestBody Map<String, Long> requestBody) {
        Long userId = requestBody.get("userId");
        List<Furniture> furnitureList = furnitureService.queryFurniturnByUserId(Math.toIntExact(userId));
        if (!furnitureList.isEmpty()) {
            return Result.success(furnitureList, "查询成功！");
        } else {
            return Result.error(ErrorCode.valueOf("未找到该用户的家具。"));
        }
    }



    @PostMapping("/updateFur")
    public ResponseEntity<Result> updateFurniture(@RequestBody Furniture updatedFurniture) {
        Furniture updated = furnitureService.updateFurniture(updatedFurniture);
        if (updated != null) {
            return ResponseEntity.ok(Result.success(updated, "更新成功！"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Result.error(ErrorCode.valueOf("未找到要更新的家具。")));
        }
    }
    private String generateErrorMessage(List<FieldError> fieldErrors) {
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            errorMessage.append(fieldError.getField())
                    .append(": ")
                    .append(fieldError.getDefaultMessage())
                    .append("; ");
        }
        return errorMessage.toString();
    }






}
