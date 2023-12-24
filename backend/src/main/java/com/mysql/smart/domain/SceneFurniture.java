package com.mysql.smart.domain;

import jakarta.persistence.*;

@Entity
@Table(name="sceneFurniture")
public class SceneFurniture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int sceneid;
    @Column
    private int furid;
    @Column
    private int furstatus;

    public SceneFurniture() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public SceneFurniture(int id, int sceneid, int furid, int furstatus) {
        this.id = id;
        this.sceneid = sceneid;
        this.furid = furid;
        this.furstatus = furstatus;
    }

    public SceneFurniture(int sceneid, int furid, int furstatus) {
        this.sceneid = sceneid;
        this.furid = furid;
        this.furstatus = furstatus;
    }

    public void setSceneid(int sceneid) {
        this.sceneid = sceneid;
    }

    public void setFurid(int furid) {
        this.furid = furid;
    }

    public void setFurstatus(int furstatus) {
        this.furstatus = furstatus;
    }

    public int getSceneid() {
        return sceneid;
    }

    public int getFurid() {
        return furid;
    }

    public int getFurstatus() {
        return furstatus;
    }
}
