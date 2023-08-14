package com.edu_platform.database.data;

public class Report {

    private Integer reportID;
    private String reportContent;
    private Integer userID;
    private Integer agencyID;
    private Integer reportState;

    public Report() {
    }

    public Report(Integer reportID, String reportContent, Integer userID, Integer agencyID, Integer reportState) {
        this.reportID = reportID;
        this.reportContent = reportContent;
        this.userID = userID;
        this.agencyID = agencyID;
        this.reportState = reportState;
    }

    public Report(Integer reportID, String reportContent, Integer userID, Integer agencyID) {
        this.reportID = reportID;
        this.reportContent = reportContent;
        this.userID = userID;
        this.agencyID = agencyID;
    }

    public Integer getReportID() {
        return reportID;
    }

    public void setReportID(Integer reportID) {
        this.reportID = reportID;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(Integer agencyID) {
        this.agencyID = agencyID;
    }

    public Integer getReportState() {
        return reportState;
    }

    public void setReportState(Integer reportState) {
        this.reportState = reportState;
    }
}
