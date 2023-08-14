package com.edu_platform.database.data;

import java.sql.Timestamp;

public class Comment {

    private Integer commentID;
    private String commentContent;
    private Integer commentState;
    private Integer userID;
    private Integer agencyID;
    private Timestamp commentTime;


    public Comment() {
    }

    public Comment(Integer commentID, String commentContent, Integer commentState, Integer userID, Integer agencyID, Timestamp commentTime) {
        this.commentID = commentID;
        this.commentContent = commentContent;
        this.commentState = commentState;
        this.userID = userID;
        this.agencyID = agencyID;
        this.commentTime = commentTime;
    }

    public Integer getCommentID() {
        return commentID;
    }

    public void setCommentID(Integer commentID) {
        this.commentID = commentID;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Integer getCommentState() {
        return commentState;
    }

    public void setCommentState(Integer commentState) {
        this.commentState = commentState;
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

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }
}
