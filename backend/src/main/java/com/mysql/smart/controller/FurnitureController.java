package com.mysql.smart.controller;

import com.mysql.smart.domain.Furniture;

import com.mysql.smart.domain.History;
import com.mysql.smart.domain.ScheduledTask;
import com.mysql.smart.service.HistoryService;
import com.mysql.smart.service.FurnitureService;
import com.mysql.smart.util.ErrorCode;
import com.mysql.smart.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.mysql.smart.util.ErrorCode.*;

@Slf4j
@RestController
@RequestMapping("/api/secure/furniture")
public class FurnitureController {
    @Autowired
    private HistoryService historyService;

    /*@Autowired
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
    }*/

    @Autowired
    private FurnitureService furnitureService;

    @PostMapping("/addFur")
    public Result<Furniture> addFurniture(@RequestBody Furniture furniture, @RequestAttribute("id") Integer userId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 处理数据校验错误
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            String errorMessage = generateErrorMessage(fieldErrors);
            return Result.error(ErrorCode.valueOf(errorMessage));
        }
        furniture.setUserId(userId);
        Furniture fur = furnitureService.addFurniture(furniture);


        // 添加历史记录
        History history = new History();
        history.setOperation(String.format("Add Furniture %s  (Type: %s)", fur.getName(), fur.getType()));
        history.setUserId(userId); // 假设有变量 userId 存储当前用户ID
        historyService.addHistory(history);
        if (fur != null) {
            return Result.success(fur, "增加成功！");
        } else {
            return Result.error(ADDFUR_ERROR);
        }


    }

    @PostMapping("/delFur")
    public Result<Furniture> delFurniture(@RequestBody Furniture furniture, @RequestAttribute("id") Integer userId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 处理数据校验错误
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            String errorMessage = generateErrorMessage(fieldErrors);
            return Result.error(valueOf(errorMessage));
        }
        furniture.setUserId(userId);
        Furniture fur = furnitureService.delFurniture(furniture);

        // 添加历史记录
        History history = new History();
        history.setOperation(String.format("Delete Furniture %s  (Type: %s)", fur.getName(), fur.getType()));
        history.setUserId(userId);
        historyService.addHistory(history);


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
    public Result<List<Furniture>> queryFurnitureByUserId(@RequestAttribute("id") Integer userId) {
        if (userId == null) {
            return Result.error(ErrorCode.valueOf("用户ID未找到。"));
        }

        List<Furniture> furnitureList = furnitureService.queryFurniturByUserId(userId);
        if (!furnitureList.isEmpty()) {
            return Result.success(furnitureList, "查询成功！");
        } else {
            return Result.error(ErrorCode.valueOf("未找到该用户的家具。"));
        }
    }



    @PostMapping("/updateFur")
    public ResponseEntity<Result> updateFurniture(@RequestBody Furniture updatedFurniture, @RequestAttribute("id") Integer userId) {
        updatedFurniture.setUserId(userId);
        Furniture updated = furnitureService.updateFurniture(updatedFurniture);



        // 添加历史记录
        History history = new History();
        history.setOperation(String.format("Update Furniture %s  (Type: %s)  (Status: %s)", updated.getName(), updated.getType(), updated.getStatus() == 1 ? "On" : "Off"));
        history.setUserId(userId);
        historyService.addHistory(history);

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
