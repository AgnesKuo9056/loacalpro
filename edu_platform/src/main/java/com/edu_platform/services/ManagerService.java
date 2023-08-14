package com.edu_platform.services;

import com.edu_platform.database.data.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

/**
 * @Author YanRanyudi
 * @CreateTime 2022/3/4 20:24
 * @Version 1.0.0
 */

@Service
public interface ManagerService {

    /**
     * 封禁用户
     * @param model
     * @param ids
     * @return
     * @throws Exception
     */
    public String banUser(Model model, String ids)throws Exception;

    /**
     * 处理投诉
     * @param model
     * @param ids
     * @return
     * @throws Exception
     */
    public String dealReport(Model model, String ids) throws Exception;
    /**
     * 解封用户
     * @param model
     * @param ids
     * @return
     * @throws Exception
     */
    public String unbanUser(Model model, String ids)throws Exception;

    /**
     * 查询所有用户信息
     * @param name
     * @return
     * @throws Exception
     */
    List<User> queryUserList(String name) throws Exception;

    /**
     * 查询所有评论信息
     * @param content
     * @return
     * @throws Exception
     */
    List<Comment> queryCommentList(String content) throws Exception;

    /**
     * 查询所有投诉信息
     * @param content
     * @return
     * @throws Exception
     */
    List<ReportRst> queryReportRstList(String content) throws Exception;

    /**
     * 评论审核不通过
     * @param model
     * @param ids
     * @return
     * @throws Exception
     */
   public String banComment(Model model, String ids) throws Exception;

    /**
     * 评论审核通过
     * @param model
     * @param ids
     * @return
     * @throws Exception
     */
    public String readComment(Model model, String ids) throws Exception;


}
