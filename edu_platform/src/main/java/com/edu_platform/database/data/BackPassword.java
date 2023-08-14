package com.edu_platform.database.data;

import javax.persistence.criteria.CriteriaBuilder;

public class BackPassword {
    private Integer userID;
    private String question;
    private String answer;

    public BackPassword() {
    }

    public BackPassword(Integer userID, String question, String answer) {
        this.userID = userID;
        this.question = question;
        this.answer = answer;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
