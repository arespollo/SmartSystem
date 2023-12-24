package com.mysql.smart.domain;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "furniture")
public class Furniture {
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int roomId) {
        this.userId = roomId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 255)
    private String type;
    @Column
    private int status;
    @Column
    private String name;
    //@NotBlank(message = "名称不能为空")
    @Column
    private int userId;
    @OneToMany(mappedBy = "furniture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScheduledTask> scheduledTasks;
    public void updateStatus(int status) {
        this.status = status;
    }

    public Furniture(String type, int status, String name, int userId) {
        this.type = type;
        this.status = status;
        this.name = name;
        this.userId = userId;
    }
    public Furniture(){

    }
// 构造方法、getter和setter省略
}