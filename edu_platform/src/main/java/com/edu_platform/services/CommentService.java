package com.edu_platform.services;

import com.edu_platform.database.data.Comment;
import com.edu_platform.database.data.UserComment;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public interface CommentService {

    /*
    * 用户查看所有审核通过的评论
    * */
    public String getAgencyComment(Integer agencyID,Model model) throws Exception;

    /**
     * 用户发表评论
     */
    public String addComment(Model model, Integer agencyID, Integer userID, String commentContent) throws Exception;

    public List<UserComment> getComment(Integer userID);
    public void deleteComment(Integer commentID);
}
