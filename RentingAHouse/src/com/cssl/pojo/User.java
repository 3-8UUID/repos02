package com.cssl.pojo;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String userName;
    private String password;
    private String telPhone;
    private String realName;
    private int isAdmin;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password, String telPhone, String realName, int isAdmin) {
        this.userName = userName;
        this.password = password;
        this.telPhone = telPhone;
        this.realName = realName;
        this.isAdmin = isAdmin;
    }

    public User(int id, String userName, String password, String telPhone, String realName, int isAdmin) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.telPhone = telPhone;
        this.realName = realName;
        this.isAdmin = isAdmin;
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

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", telPhone='" + telPhone + '\'' +
                ", realName='" + realName + '\'' +
                ", isAdmin='" + isAdmin + '\'' +
                '}';
    }
}
