package com.mysql.smart.domain;

import jakarta.persistence.*;

@Entity
@Table(name="sceneFurniture")
public class SceneFurniture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int sceneId;
    @Column
    private int furId;
    @Column
    private int furStatus;

    public SceneFurniture() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public SceneFurniture(int id, int sceneId, int furId, int furStatus) {
        this.id = id;
        this.sceneId = sceneId;
        this.furId = furId;
        this.furStatus = furStatus;
    }

    public SceneFurniture(int sceneId, int furId, int furStatus) {
        this.sceneId = sceneId;
        this.furId = furId;
        this.furStatus = furStatus;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public void setFurId(int furId) {
        this.furId = furId;
    }

    public void setFurStatus(int furStatus) {
        this.furStatus = furStatus;
    }

    public int getSceneId() {
        return sceneId;
    }

    public int getFurId() {
        return furId;
    }

    public int getFurStatus() {
        return furStatus;
    }
}
