package com.edu_platform.services;

import com.edu_platform.database.data.BackPassword;
import com.edu_platform.database.data.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public boolean login(User user);

    public User getuser(String username);

    public String getPassword(String username);

    public boolean check_exist(User user);

    public boolean register(User user, BackPassword backPassword);

    public boolean changePhone(Integer userID, String userPhone);

    public boolean changePass(Integer userID, String userPassword);

    public BackPassword getQuestionAnswer(String username);
}
