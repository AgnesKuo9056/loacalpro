package com.edu_platform.database.data;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "comment")
public class UserComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer commentID;
    @Column(name = "comment_content")
    private String commentContent;
    @Column(name = "comment_state")
    private Integer commentState;
    @Column(name = "user_id")
    private Integer userID;

    private String agencyName;
    @Column(name = "comment_time")
    private Timestamp commentTime;

    public UserComment() {
    }

    public UserComment(Integer commentID, String commentContent, Integer commentState, Integer userID, String agencyName, Timestamp commentTime) {
        this.commentID = commentID;
        this.commentContent = commentContent;
        this.commentState = commentState;
        this.userID = userID;
        this.agencyName = agencyName;
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

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }
}
