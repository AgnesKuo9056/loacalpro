package com.edu_platform.controller;

import com.edu_platform.utils.SessionUtils;
import com.edu_platform.database.data.*;
import com.edu_platform.services.AgencyService;
import com.edu_platform.services.ManagerService;
import com.edu_platform.utils.SessionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author YanRanyudi
 * @CreateTime 2022/3/4 20:37
 * @Version 1.0.0
 */
@Controller
public class ManagerController {

    SessionUtils sessionUtils;
    //注入服务接口对象
    @Resource
    private ManagerService managerService;

    /**
     * 显示所有用户信息
     * @param name
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/userlist")
    public String userList(String name, Model model,HttpServletRequest request) throws Exception {
        String myname = sessionUtils.getSession(request).getUserName();
        model.addAttribute("myname",myname);
        List<User> result = managerService.queryUserList(name);
        System.out.println("managerServiced");
        model.addAttribute("user",result);
        System.out.println(result.get(0));
        return "user-list";
    }

    /**
     * 封禁用户
     * @param model
     * @param ids
     * @return
     */
    @RequestMapping("/banuser")
    public String banuser(Model model,@RequestParam("id") String ids) {
        try {
            System.out.println("ban!!!!");
            String nextPath = managerService.banUser(model,ids);
            System.out.println("SQLbaned!!!!!");
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "redirect:http://localhost:8080/userlist";
    }

    /**
     * 解封用户
     * @param model
     * @param ids
     * @return
     */
    @RequestMapping("/unbanuser")
    public String unbanuser(Model model,@RequestParam("id") String ids) {
        try {
            System.out.println("unban!!!!");
            String nextPath = managerService.unbanUser(model,ids);
            System.out.println("SQLbaned!!!!!");
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "redirect:http://localhost:8080/userlist";
    }
    /**
     * 处理投诉
     * @param model
     * @param ids
     * @return
     */
    @RequestMapping("/dealreport")
    public String dealreport(Model model,@RequestParam("id") String ids) {
        try {
            System.out.println("DealController");
            String nextPath = managerService.dealReport(model,ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:http://localhost:8080/reportlist";
    }


    /**
     * 显示所有评论信息
     * @param content
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/commentlist")
    public String commentList(String content, Model model,HttpServletRequest request) throws Exception {
        String myname = sessionUtils.getSession(request).getUserName();
        model.addAttribute("myname",myname);
        List<Comment> result = managerService.queryCommentList(content);
        System.out.println("managerServiced");
        model.addAttribute("comment",result);
        System.out.println(result.get(0));
        return "comment-list";
    }

    /**
     * 显示所有投诉信息
     * @param content
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/reportlist")
    public String reportList(String content, Model model, HttpServletRequest request) throws Exception {
        String myname = sessionUtils.getSession(request).getUserName();
        model.addAttribute("myname",myname);
        List<ReportRst> result = managerService.queryReportRstList(content);
        System.out.println("managerRstServiced");
        model.addAttribute("report",result);
        System.out.println(result.get(0));
        return "report-list";
    }

    /**
     * 审核评论（不通过）
     * @param model
     * @param ids
     * @return
     */
    @RequestMapping("/bancomment")
    public String bancomment(Model model,@RequestParam("id") String ids) {
        try {
            System.out.println("ban!!!!");
            String nextPath = managerService.banComment(model,ids);
            System.out.println("SQLbaned!!!!!");
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "redirect:http://localhost:8080/commentlist";
    }

    /**
     * 审核评论（通过）
     * @param model
     * @param ids
     * @return
     */
    @RequestMapping("/readcomment")
    public String readcomment(Model model,@RequestParam("id") String ids) {
        try {
            System.out.println("read!!!!");
            String nextPath = managerService.readComment(model,ids);
            System.out.println("SQLread!!!!!");
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "redirect:http://localhost:8080/commentlist";
    }
}
