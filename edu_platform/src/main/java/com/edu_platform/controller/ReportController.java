package com.edu_platform.controller;

import com.edu_platform.services.impl.ReportServiceImpl;
import com.edu_platform.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class ReportController {
    @Autowired
    ReportServiceImpl reportService;
    SessionUtils sessionUtils;

    @GetMapping("/report")
    public String Report(Model model,HttpServletRequest request){
        String myname = sessionUtils.getSession(request).getUserName();
        model.addAttribute("myname",myname);
        List<String> agency = reportService.getAgency();
        model.addAttribute("agency",agency);
        return "opinion";
    }

    @GetMapping("/report_success")
    public String Report_success(@RequestParam("advise") String advise,
                                 @RequestParam("report_agency") String report_agency,
                                 Model model,
                                 HttpServletRequest request, HttpServletResponse response) throws IOException {
        //        选择机构为空判断+界面提示
        System.out.println(report_agency.equals("请选择机构"));
        if(report_agency.equals("请选择机构")){
            model.addAttribute("msg1", "请选择机构！");
            List<String> agency = reportService.getAgency();
            model.addAttribute("agency",agency);
            return "opinion";
        }
        //        意见建议为空判断+界面提示
        if(advise.isEmpty()){
            model.addAttribute("msg3", "请输入您的意见和建议！");
            List<String> agency = reportService.getAgency();
            model.addAttribute("agency",agency);
            return "opinion";
        }

        Integer userId = sessionUtils.getSession(request).getUserID();
        boolean agency = reportService.report(advise,userId,report_agency);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out=response.getWriter();
        if(agency==true){
            out.print("<script>alert('提交成功');</script>");
            return "opinion";
        }else {
            out.print("<script>alert('提交失败');history.go(-1);</script>");
            return null;
        }
    }

}
