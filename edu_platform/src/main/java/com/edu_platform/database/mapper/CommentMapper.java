package com.edu_platform.database.mapper;

import com.edu_platform.database.data.Comment;
import com.edu_platform.database.data.UserComment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {


    /*
    * 欣雨的mapper
    * */
    @Select("Select comment_id,comment_content,comment_state,user_id,agency_name,comment_time from comment natural join agency where user_ID=#{userID}")
    public List<UserComment> getCommentByUserID(Integer userID);

    @Delete("DELETE FROM COMMENT WHERE comment_id=#{commentID}")
    public void deleteCommentBycommentID(Integer commentID);

    /**
     *普通用户查看所有的评论
     */
    @Select("select comment_time,comment_content from comment where comment_state=2 and agency_id=#{agencyID} order by comment_time desc")
    public List<Comment> getAgencyComment(Integer agencyID) throws Exception;


    /*
    * 用户增加自己的评论
    * */
    @Insert("insert into comment(comment_id,comment_content,comment_state,comment_time,user_id,agency_id) " +
            "values(#{commentID}, #{commentContent}, #{commentState}, #{commentTime}, #{userID}, #{agencyID})")
    public void addComment(Comment comment) throws Exception;
}
