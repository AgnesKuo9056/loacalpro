package com.edu_platform.utils;

import com.edu_platform.database.data.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static User getSession(HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("User");
        return user;
    }

}