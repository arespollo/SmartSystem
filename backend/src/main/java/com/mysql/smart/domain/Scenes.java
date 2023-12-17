package com.mysql.smart.domain;

import jakarta.persistence.*;

@Entity
public class Scenes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private int userid;
    @Column
    private int status;

    public Scenes(int id, String name, int userid, int status) {
        this.id = id;
        this.name = name;
        this.userid = userid;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUserid() {
        return userid;
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

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
