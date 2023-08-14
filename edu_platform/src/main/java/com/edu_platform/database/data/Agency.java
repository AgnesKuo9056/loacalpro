package com.edu_platform.database.data;

import java.sql.Timestamp;
import java.util.Date;

public class Agency {

    private Integer agencyID;
    private String agencyName;
    private String agencyAddress;
    private String agencyPhone;
    private String agencyImg;
    private String agencyType;
    private String agencyIncome;
    private Date agencyTime;
    private String agencyX;
    private String agencyY;

    public Agency(){
    }

    public Agency(Integer agencyID, String agencyName, String agencyAddress, String agencyPhone, String agencyImg, String agencyType, String agencyIncome, Date agencyTime,String agencyX,String agencyY) {
        this.agencyID = agencyID;
        this.agencyName = agencyName;
        this.agencyAddress = agencyAddress;
        this.agencyPhone = agencyPhone;
        this.agencyImg = agencyImg;
        this.agencyType = agencyType;
        this.agencyIncome = agencyIncome;
        this.agencyTime = agencyTime;
        this.agencyX = agencyX;
        this.agencyY = agencyY;

    }
    public Agency( String agencyName, String agencyAddress, String agencyPhone, String agencyImg, String agencyType, String agencyIncome, Date agencyTime,String agencyX,String agencyY) {
        this.agencyName = agencyName;
        this.agencyAddress = agencyAddress;
        this.agencyPhone = agencyPhone;
        this.agencyImg = agencyImg;
        this.agencyType = agencyType;
        this.agencyIncome = agencyIncome;
        this.agencyTime = agencyTime;
        this.agencyX = agencyX;
        this.agencyY = agencyY;

    }

    public Integer getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(Integer agencyID) {
        this.agencyID = agencyID;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyAddress() {
        return agencyAddress;
    }

    public void setAgencyAddress(String agencyAddress) {
        this.agencyAddress = agencyAddress;
    }

    public String getAgencyPhone() {
        return agencyPhone;
    }

    public void setAgencyPhone(String agencyPhone) {
        this.agencyPhone = agencyPhone;
    }

    public String getAgencyImg() {
        return agencyImg;
    }

    public void setAgencyImg(String agencyImg) {
        this.agencyImg = agencyImg;
    }

    public String getAgencyType() {
        return agencyType;
    }

    public void setAgencyType(String agencyType) {
        this.agencyType = agencyType;
    }

    public String getAgencyIncome() {
        return agencyIncome;
    }

    public void setAgencyIncome(String agencyIncome) {
        this.agencyIncome = agencyIncome;
    }

    public Date getAgencyTime() {
        return agencyTime;
    }

    public void setAgencyTime(Timestamp agencyTime) {
        this.agencyTime = agencyTime;
    }

    public String getAgencyX() {
        return agencyX;
    }

    public void setAgencyX(String agencyX) {
        this.agencyX = agencyX;
    }

    public String getAgencyY() {
        return agencyY;
    }

    public void setAgencyY(String agencyY) {
        this.agencyY = agencyY;
    }

    public String MytoString() {
        return "Agency{" +
                "agencyID=" + agencyID +
                ", agencyName='" + agencyName + '\'' +
                ", agencyAddress='" + agencyAddress + '\'' +
                ", agencyPhone='" + agencyPhone + '\'' +
                ", agencyType='" + agencyType + '\'' +
                ", agencyIncome='" + agencyIncome + '\'' +
                ", agencyTime=" + agencyTime +
                ", agencyX='" + agencyX + '\'' +
                ", agencyY='" + agencyY + '\'' +
                '}';
    }
}
