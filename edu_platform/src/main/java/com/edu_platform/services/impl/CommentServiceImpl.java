package com.edu_platform.services.impl;

import com.edu_platform.database.data.Agency;
import com.edu_platform.database.data.Comment;
import com.edu_platform.database.data.UserComment;
import com.edu_platform.database.mapper.CommentMapper;
import com.edu_platform.services.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public String getAgencyComment(Integer agencyID, Model model) throws Exception{
        List<Comment> list=commentMapper.getAgencyComment(agencyID);
        model.addAttribute("comments", list);
        return "agency-detail";
    }

    @Override
    public String addComment(Model model, Integer agencyID, Integer userID, String commentContent) throws Exception{
        Comment comment=new Comment(null,commentContent,1,userID,agencyID,new Timestamp(System.currentTimeMillis()));
        commentMapper.addComment(comment);
        return "agency-detail";
    }

    @Override
    public List<UserComment> getComment(Integer userID){
        return commentMapper.getCommentByUserID(userID);
    }
    @Override
    public void deleteComment(Integer commentID){
        commentMapper.deleteCommentBycommentID(commentID);
    }
}
