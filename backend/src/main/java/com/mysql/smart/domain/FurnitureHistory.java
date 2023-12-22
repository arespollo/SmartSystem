package com.mysql.smart.domain;
import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "furniture_history")
public class FurnitureHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String operation; // 操作类型，如"新增"、"删除"、"更新"、"查询"

    @Column(nullable = false)
    private int userId;

    public FurnitureHistory(int id, LocalDateTime date, String operation, int userId) {
        this.id = id;
        this.date = date;
        this.operation = operation;
        this.userId = userId;
    }
    public FurnitureHistory() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return date;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.date = timestamp;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    // 构造方法、getter和setter省略
}
