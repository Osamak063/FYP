package com.example.osamakhalid.thesmartinterviewer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Osama Khalid on 1/11/2019.
 */

public class User {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("lname")
    @Expose
    private String lname;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("education")
    @Expose
    private String education;
    @SerializedName("cv")
    @Expose
    private String cv;
    @SerializedName("picture")
    @Expose
    private String picture;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Integer id, String fname, String lname, String address, String education, String cv, String picture) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.education = education;
        this.cv = cv;
        this.picture = picture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
