package com.cssl.pojo;

import java.io.Serializable;
import java.util.Date;

public class HouseInfo implements Serializable {
    private int id;
    private String username;
    private String tname;
    private String title;
    private String description;
    private double price;
    private Date pubdate;
    private double floorage;
    private String sname;
    private String dname;

    public HouseInfo() {
    }

    public HouseInfo(int id, String username, String tname, String title,
                     String description, double price, Date pubdate, double floorage, String sname, String dname) {
        this.id = id;
        this.username = username;
        this.tname = tname;
        this.title = title;
        this.description = description;
        this.price = price;
        this.pubdate = pubdate;
        this.floorage = floorage;
        this.sname = sname;
        this.dname = dname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public double getFloorage() {
        return floorage;
    }

    public void setFloorage(double floorage) {
        this.floorage = floorage;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public String toString() {
        return "HouseInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", tname='" + tname + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", pubdate=" + pubdate +
                ", floorage=" + floorage +
                ", sname='" + sname + '\'' +
                ", dname='" + dname + '\'' +
                '}';
    }
}
