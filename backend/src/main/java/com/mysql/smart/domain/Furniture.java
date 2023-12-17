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


    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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
    private int roomId;
    @OneToMany(mappedBy = "furniture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScheduledTask> scheduledTasks;


    public Furniture(String type, int status, String name, int roomId) {
        this.type = type;
        this.status = status;
        this.name = name;
        this.roomId = roomId;
    }
    public Furniture(){

    }
// 构造方法、getter和setter省略
}