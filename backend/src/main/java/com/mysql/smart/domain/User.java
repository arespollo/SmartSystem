package com.mysql.smart.domain;

import com.mysql.smart.util.PwdHash;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false, length = 255)
    private String userName;
    @Column(nullable = false, length = 255)
    private String password;
    @Column
    private int leaveHome;
    @Column
    private int sleep;
    @Column
    private int wake;
    @Column
    private int returnHome;
    public void setLeaveHome(int leaveHome) {
        this.leaveHome = leaveHome;
    }

    public void setReturnHome(int returnHome) {
        this.returnHome = returnHome;
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }

    public void setWake(int wake) {
        this.wake = wake;
    }

    public void updateUserSceneStatus(long id, String scene, int status) {
        // 根据传入的字段来更新用户信息
        this.setSleep(0);
        this.setWake(0);
        this.setLeaveHome(0);
        this.setReturnHome(0);
        switch (scene) {
            case "leaveHome":
                this.setLeaveHome(status);
                break;
            case "returnHome":
                this.setReturnHome(status);
                break;
            case "sleep":
                this.setSleep(status);
                break;
            case "wake":
                this.setWake(status);
                break;
            default:
                break;
        }
    }

    public int getLeaveHome() {
        return leaveHome;
    }

    public int getReturnHome() {
        return returnHome;
    }

    public int getSleep() {
        return sleep;
    }

    public int getWake() {
        return wake;
    }


    private static final PwdHash pwdHash = new PwdHash();

    public User() {
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.userName = username;
        this.password = password;
    }

    public static PwdHash getPwdHash() {
        return pwdHash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
