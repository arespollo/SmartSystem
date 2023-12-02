package com.mysql.smart.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "sceneStatus")
public class SceneStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 255)
    private String scene;
    @Column
    private int status;
    @JoinColumn
    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public SceneStatus() {
        // 无参构造函数
    }
    public SceneStatus(int userId, String scene, int status) {
        this.userId = userId;
        this.scene = scene;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getScene() {
        return scene;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

}
