package com.edu_platform.database.data;

public class User {

    private Integer userID;
    private String userName;
    private String userPassword;
    private String userPhone;
    private Integer userRole;
    private String userState;


    public User() {
    }

    public User(Integer userID, String userName, String userPassword, String userPhone, Integer userRole, String userState) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userRole = userRole;
        this.userState = userState;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }
}
