package com.pinyun.java.gradle.domain;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "phone_num")
    private String phoneNnum;

    @Column(name = "password")
    private String password; // Hashed and salted password

    public User(Long userId, String userName, String phoneNnum, String password) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNnum = phoneNnum;
        this.password = password;
    }



    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNnum() {
        return phoneNnum;
    }

    public void setPhoneNnum(String phoneNnum) {
        this.phoneNnum = phoneNnum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
