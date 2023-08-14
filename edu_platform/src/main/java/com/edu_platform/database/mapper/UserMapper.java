package com.edu_platform.database.mapper;

import com.edu_platform.database.data.Agency;
import com.edu_platform.database.data.BackPassword;
import com.edu_platform.database.data.Report;
import com.edu_platform.database.data.User;
import org.apache.ibatis.annotations.*;

/*import javax.persistence.criteria.CriteriaBuilder;*/
import java.util.List;
import java.util.Map;


@Mapper
public interface UserMapper {
    @Update("update user set user_phone=#{userPhone}  where user_id=#{userID}")
    public int changePhoneUser(Integer userID, String userPhone);

    @Update("update user set user_psw=#{userPassword} where user_id=#{userID}")
    public int changePassUser(Integer userID, String userPassword);
    /**
     * 根据用户name得到User对象
     */
    @Select("Select * from user where user_name=#{userName}")
    public User getUserByUserID(String userName) throws Exception;

    @Insert("insert into user(user_name,user_psw) values(#{userName},#{userPassword})")
    public int insertUser(User user);

    @Insert("insert into backpassword(user_id,question,answer) values(#{userID},#{question},#{answer})")
    public int insertBackPassword(BackPassword backPassword);

    @Select("select * from user where user_name=#{userName}")
    public User getUserByName(String userName);

    @Select("Select user_psw from user where user_name=#{userName}")
    public String getUserPasswordByName(String name);

    @Select("select * from backpassword where user_id=#{userID}")
    public BackPassword getBackpassByName(Integer userID);
}
