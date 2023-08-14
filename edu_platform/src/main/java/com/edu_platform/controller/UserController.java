package com.edu_platform.controller;


import com.edu_platform.database.data.BackPassword;
import com.edu_platform.database.data.Comment;
import com.edu_platform.database.data.User;
import com.edu_platform.database.data.UserComment;
import com.edu_platform.services.AgencyService;
import com.edu_platform.services.CommentService;
import com.edu_platform.services.impl.UserServiceImpl;
import com.edu_platform.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@Controller
public class UserController {
    @Autowired
    UserServiceImpl userService;
    SessionUtils sessionUtils;

    @Autowired
    CommentService commentService;
    @Autowired
    AgencyService agencyService;
    //-----------------------------登录注册功能
    //登录：
    @RequestMapping("/login")
    public String result(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         Model model,
                         HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(10*60);

        User strs = userService.getuser(username);
        session.setAttribute("User",strs);
        String name = sessionUtils.getSession(request).getUserName();
        model.addAttribute("myname",name);

        Integer smallNumber=agencyService.countAgencySmall();
        model.addAttribute("smallNumber",smallNumber);

        Integer middleNumber=agencyService.countAgencyMiddle();
        model.addAttribute("middleNumber",middleNumber);

        Integer collegeNumber=agencyService.countAgencyCollege();
        model.addAttribute("collegeNumber",collegeNumber);

        Integer otherNumber=agencyService.countAgencyOther();
        model.addAttribute("otherNumber",otherNumber);

        System.out.println(name);
        User user = new User();
        user.setUserName(username);
        user.setUserPassword(password);
        String state = strs.getUserState();
        if(state == "是"){
            model.addAttribute("msg1","该账号被封禁!");
            return "sign-in";
        }
        Integer role =  strs.getUserRole();
        boolean flag = userService.login(user);

        if(flag==true){
            if(role == 0){
                return "redirect:http://localhost:8080/agencylist";
            }else if(role == 1){
                /*return "redirect:/main";*/
                return "agency-chart";
            }else return "sign-in";
        }else {
            model.addAttribute("msg","用户名或密码错误!");
            return "sign-in";
        }
    }
    //注册：
    @RequestMapping("/register")
    public String register(@RequestParam("username") String username,
                      @RequestParam("password") String password,
                      @RequestParam("password_check") String password_check,
                           @RequestParam("question") String question,
                           @RequestParam("answer") String answer,
                      Model model, HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        if (!(password.equals(password_check))) {
            model.addAttribute("msg1", "两次输入密码不一致!");
            return "sign-up";
        }

        User user = new User();
        user.setUserName(username);
        user.setUserPassword(password);
        BackPassword backPassword = new BackPassword();
        backPassword.setQuestion(question);
        backPassword.setAnswer(answer);
        boolean exist = userService.check_exist(user);
        System.out.println(exist);
        if(!exist){
            model.addAttribute("msg2", "该用户名已存在!");
            return "sign-up";
        }

        boolean flag = userService.register(user,backPassword);
        System.out.println(flag);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out=response.getWriter();
        if(flag==true){
            out.print("<script>alert('注册成功');</script>");
            return "sign-in";
        }else {
            out.print("<script>alert('注册失败');</script>");
            return "sign-up";
        }
    }

    @GetMapping("/changePass")
    public String changePassword(@RequestParam("password") String password,
                         @RequestParam("newPassword") String newPassword,
                         @RequestParam("checkPassword") String checkPassword,
                         Model model,
                         HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out=response.getWriter();
        if (!(newPassword.equals(checkPassword))) {
            model.addAttribute("msg1", "两次输入密码不一致!");
            return Person(model,request);
        }
        String username = sessionUtils.getSession(request).getUserName();
        String passWord = userService.getPassword(username);

        if(passWord == null||!(passWord.equals(password))){
            model.addAttribute("msg2", "用户密码错误，请重新输入!");
            out.print("<script>alert('用户密码错误，请重新输入!');</script>");
            return Person(model,request);
        }
        Integer userId = sessionUtils.getSession(request).getUserID();
        System.out.println(userId);
        boolean flag = userService.changePass(userId,newPassword);
        System.out.println(flag);
        if(flag==true){
            out.print("<script>alert('修改密码成功');</script>");
            return Person(model,request);
        }else {
            out.print("<script>alert('修改密码失败');history.go(-1);</script>");
            return null;
        }
    }

    @GetMapping("/changePhone")
    public String changePhone(@RequestParam("phone") String phone,
                         Model model,
                         HttpServletRequest request,HttpServletResponse response) throws IOException {
        Integer userId = sessionUtils.getSession(request).getUserID();
        String userphone = phone;
        System.out.println(userId);

        boolean flag = userService.changePhone(userId,userphone);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out=response.getWriter();
        if(flag==true){
            out.print("<script>alert('修改个人信息成功');</script>");
            return Person(model,request);
        }else {
            out.print("<script>alert('修改个人信息失败');history.go(-1);</script>");
            return null;
        }
    }
    @GetMapping("/personcenter")
    public String Person(Model model,
                           HttpServletRequest request){
        String myname = sessionUtils.getSession(request).getUserName();
        model.addAttribute("myname",myname);

        User user_for = userService.getuser(myname);
        model.addAttribute("user_for",user_for);

        String password_for = userService.getPassword(myname);
        model.addAttribute("password_for",password_for);

        Integer userId = sessionUtils.getSession(request).getUserID();
        System.out.println(userId);
        List<UserComment> comment = commentService.getComment(userId);
        model.addAttribute("comment",comment);

        return "person-center";
    }
    @GetMapping("/delete")
    public String delComment(@RequestParam("commentID")Integer commentID,
                             Model model,
                             HttpServletRequest request){
        try{
            commentService.deleteComment(commentID);
            System.out.println(commentID);
            return Person(model,request);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }
    @GetMapping("/forgetPassword")
    public String forgetPass(@RequestParam("username")String username,Model model,
                             HttpServletRequest request){
        if(userService.getuser(username)==null){
            model.addAttribute("msg","用户名不存在！");
            return "forget-password";
        }
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(10*60);

        User strs = userService.getuser(username);
        session.setAttribute("User",strs);
        String question = userService.getQuestionAnswer(username).getQuestion();
        model.addAttribute("name",username);
        model.addAttribute("question",question);
        return "back-password";
    }

    @GetMapping("/backPassword")
    public String backPass(@RequestParam("username")String username,
                           @RequestParam("answer")String answer,Model model,
                           HttpServletResponse response) throws IOException {
        String real_answer = userService.getQuestionAnswer(username).getAnswer();
        String password = userService.getPassword(username);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out=response.getWriter();
        if(real_answer.equals(answer)){
            model.addAttribute("password",password);
            out.print("<script>alert('验证成功！');</script>");
;            return "get-password";
        }else{
            out.print("<script>alert('验证失败');</script>");
            return "forget-password";
        }
    }
    @GetMapping("/resetPassword")
    public String resetPass(@RequestParam("password") String password,
                            @RequestParam("password_check") String password_check,
                            Model model,HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out=response.getWriter();
        if (!(password.equals(password_check))) {
            model.addAttribute("msg1", "两次输入密码不一致!");
            return "get-password";
        }
        Integer id = sessionUtils.getSession(request).getUserID();
        userService.changePass(id,password);
        out.print("<script>alert('提交成功!');</script>");
        return "sign-in";
    }
}
