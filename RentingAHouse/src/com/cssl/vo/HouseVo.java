package com.cssl.vo;

/**
 * 住房信息类
 */
public class HouseVo {

    private int id;
    private int user_id;
    private int type_id;
    private String title;
    private String description;
    private double price;
    private String pubdate;
    private double floorage;
    private int street_id;
    private String contact;
    private String fileName;

    public HouseVo(int id, int user_id, int type_id, String title, String description, double price, String pubdate, double floorage, int street_id, String contact, String fileName) {
        this.id = id;
        this.user_id = user_id;
        this.type_id = type_id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.pubdate = pubdate;
        this.floorage = floorage;
        this.street_id = street_id;
        this.contact = contact;
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public HouseVo() {
    }

    public HouseVo(int user_id, int type_id, String title, String description,
                   double price, String pubdate, double floorage, int street_id, String contact) {
        this.user_id = user_id;
        this.type_id = type_id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.pubdate = pubdate;
        this.floorage = floorage;
        this.street_id = street_id;
        this.contact = contact;
    }

    public HouseVo(int user_id, int type_id, String title, String description,
                   double price, String pubdate, double floorage, int street_id) {
        this.user_id = user_id;
        this.type_id = type_id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.pubdate = pubdate;
        this.floorage = floorage;
        this.street_id = street_id;
    }

    public HouseVo(int id, int user_id, int type_id, String title, String description, double price, String pubdate,
                   double floorage, int street_id, String contact) {
        this.id = id;
        this.user_id = user_id;
        this.type_id = type_id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.pubdate = pubdate;
        this.floorage = floorage;
        this.street_id = street_id;
        this.contact = contact;
    }

    public HouseVo(int id, int user_id, int type_id, String title,
                   String description, double price, String pubdate, double floorage, int street_id) {
        this.id = id;
        this.user_id = user_id;
        this.type_id = type_id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.pubdate = pubdate;
        this.floorage = floorage;
        this.street_id = street_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
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

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public double getFloorage() {
        return floorage;
    }

    public void setFloorage(double floorage) {
        this.floorage = floorage;
    }

    public int getStreet_id() {
        return street_id;
    }

    public void setStreet_id(int street_id) {
        this.street_id = street_id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "HouseVo{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", type_id=" + type_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", pubdate='" + pubdate + '\'' +
                ", floorage=" + floorage +
                ", street_id=" + street_id +
                ", contact='" + contact + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}

