package com.mysql.smart.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="scenes")
public class Scenes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private int userId;
    @Column
    private int status;
    public Scenes(){

    }
    public Scenes(int id, String name, int userId, int status) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public int getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @OneToMany(mappedBy = "sceneid", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SceneFurniture> sceneFurnitureList;

    public List<SceneFurniture> getSceneFurnitureList() {
        return sceneFurnitureList;
    }

    public void setSceneFurnitureList(List<SceneFurniture> sceneFurnitureList) {
        this.sceneFurnitureList = sceneFurnitureList;
    }
}
