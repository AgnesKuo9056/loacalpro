package com.edu_platform.services.impl;

import com.edu_platform.database.data.*;
import com.edu_platform.database.mapper.ManagerMapper;
import com.edu_platform.services.ManagerService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author YanRanyudi
 * @CreateTime 2022/3/4 20:26
 * @Version 1.0.0
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    @Resource ManagerMapper managerMapper;

    @Override
    public String banUser(Model model, String ids) throws Exception {
        managerMapper.banUser(ids);
        return "";
    }
    @Override
    public String dealReport(Model model, String ids) throws Exception {
        managerMapper.dealReport(ids);
        return "";
    }
    @Override
    public String unbanUser(Model model, String ids) throws Exception {
        managerMapper.unbanUser(ids);
        return "";
    }

    @Override
    public List<User> queryUserList(String name) throws Exception {

        return managerMapper.queryUserList(name);
    }

    @Override
    public List<Comment> queryCommentList(String content) throws Exception {
        return managerMapper.queryCommentList(content);
    }
    @Override
    public List<ReportRst> queryReportRstList(String content) throws Exception {
        return managerMapper.queryReportRstList(content);
    }

    @Override
    public String banComment(Model model, String ids) throws Exception {
        managerMapper.banComment(ids);
        return "";
    }
    @Override
    public String readComment(Model model, String ids) throws Exception {
        managerMapper.readComment(ids);
        return "";
    }
}
