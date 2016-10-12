package com.elife.classproject.network;

/**
 * Created by tzhang on 2016/9/10.
 */
public class UserModel {

    private String userPhone;
    private String userName;
    private String userImg;
    private String userAge;
    private String userSignature;
    private String userPlace;

    public UserModel() {
    }

    public UserModel(String userAge, String userImg, String userName, String userPhone, String userPlace, String userSignature) {
        this.userAge = userAge;
        this.userImg = userImg;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userPlace = userPlace;
        this.userSignature = userSignature;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPlace() {
        return userPlace;
    }

    public void setUserPlace(String userPlace) {
        this.userPlace = userPlace;
    }

    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
    }
}
