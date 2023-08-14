package com.edu_platform.database.data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Author YanRanyudi
 * @CreateTime 2022/3/5 17:45
 * @Version 1.0.0
 */
@Table(name="report")
public class ReportRst {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reportID;
    @Column(name="report_content")
    private String reportContent;
    private String userName;
    private String agencyName;
    private String userPhone;
    private String agencyPhone;
    @Column(name="report_state")
private String reportState;
    public ReportRst() {
    }

    public ReportRst(Integer reportID, String reportContent, String userName, String agencyName, String userPhone, String agencyPhone, String reportState) {
        this.reportID = reportID;
        this.reportContent = reportContent;
        this.userName = userName;
        this.agencyName = agencyName;
        this.userPhone = userPhone;
        this.agencyPhone = agencyPhone;
        this.reportState = reportState;
    }

    public ReportRst(Integer reportID, String reportContent, String userName, String agencyName, String userPhone, String agencyPhone) {
        this.reportID = reportID;
        this.reportContent = reportContent;
        this.userName = userName;
        this.agencyName = agencyName;
        this.userPhone = userPhone;
        this.agencyPhone = agencyPhone;
    }

    public String getReportState() {
        return reportState;
    }

    public void setReportState(String reportState) {
        this.reportState = reportState;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getAgencyPhone() {
        return agencyPhone;
    }

    public void setAgencyPhone(String agencyPhone) {
        this.agencyPhone = agencyPhone;
    }

    @Override
    public String toString() {
        return "ReportRst{" +
                "reportID=" + reportID +
                ", reportContent='" + reportContent + '\'' +
                ", userName='" + userName + '\'' +
                ", agencyName='" + agencyName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", agencyPhone='" + agencyPhone + '\'' +
                '}';
    }
}
