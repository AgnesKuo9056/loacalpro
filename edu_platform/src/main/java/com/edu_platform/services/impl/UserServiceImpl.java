package com.edu_platform.services.impl;

import com.edu_platform.database.data.BackPassword;
import com.edu_platform.database.data.User;

import com.edu_platform.database.mapper.UserMapper;
import com.edu_platform.services.AgencyService;
import com.edu_platform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public boolean login(User user){
        String name = user.getUserName();
        String password = user.getUserPassword();
        System.out.println(name);
        System.out.println(password);
        String u1 =  userMapper.getUserPasswordByName(name);
        System.out.println("***");
        System.out.println(u1);

        if(u1==null){
            return false;
        }else{
            if(u1.equals(password)){
                return true;
            }else{
                return false;
            }
        }
    }

    @Override
    public User getuser(String username){
        return userMapper.getUserByName(username);
    }
    public String getPassword(String username){
        return userMapper.getUserPasswordByName(username);
    }
    public boolean check_exist(User user){
        String name = user.getUserName();
        String u1 =  userMapper.getUserPasswordByName(name);
        System.out.println("__________");
        System.out.println(u1);
        if(u1 == null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean register(User user, BackPassword backPassword) {
        int x = userMapper.insertUser(user);

        Integer id = userMapper.getUserByName(user.getUserName()).getUserID();
        backPassword.setUserID(id);
        int y = userMapper.insertBackPassword(backPassword);
        if(x>0&&y>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean changePhone(Integer userID, String userPhone){
        Integer id = userID;
        String phone = userPhone;
        int x = userMapper.changePhoneUser(id,phone);
        if(x>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean changePass(Integer userID, String userPassword){
        Integer id = userID;
        String password = userPassword;
        int x = userMapper.changePassUser(id,password);
        if(x>0){
            return true;
        }else {
            return false;
        }
    }
    @Override
    public BackPassword getQuestionAnswer(String username) {
        Integer id = userMapper.getUserByName(username).getUserID();
        return userMapper.getBackpassByName(id);
    }
}