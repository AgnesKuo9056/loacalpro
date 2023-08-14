package com.edu_platform.controller;

import com.edu_platform.database.data.Agency;
import com.edu_platform.database.data.Comment;
import com.edu_platform.services.AgencyService;
import com.edu_platform.services.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.edu_platform.utils.SessionUtils;
import org.springframework.web.multipart.MultipartFile;


@Controller

public class AgencyController {

    SessionUtils sessionUtils;
    @Resource
    private AgencyService agencyService;
    @Resource
    private CommentService commentService;

    /*
    * 访问agency-table页面
    * */
    @RequestMapping("/agency-table")
    public String agencyTable(Model model, HttpServletRequest request){
        String myname = sessionUtils.getSession(request).getUserName();
        model.addAttribute("myname",myname);
        try{
            String nextPath=agencyService.getAllAgency(model);
            return nextPath;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /*
    * 从agency-table页面带着机构的id跳转到详情页
    * */
    @RequestMapping(value = "/agency-detail")
    public String toAgencyDetail(Model model,@RequestParam("agencyID")Integer agencyID, HttpServletRequest request){
        String myname = sessionUtils.getSession(request).getUserName();
        model.addAttribute("myname",myname);
        try {
            //用户查看所有的机构
            Agency agency=agencyService.getAgencyByID(agencyID);
            //用户查看一个机构下的所有评论
            String nextPath=commentService.getAgencyComment(agencyID,model);

            model.addAttribute("agency",agency);
            return "agency-detail";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    @RequestMapping("/agency-comment")
    public String addComment(Model model,@RequestParam("agencyID")Integer agencyID,
                             @RequestParam("commentContent")String commentContent,
                             HttpServletRequest request) throws Exception {


            //用户增加评论
            Integer userID= SessionUtils.getSession(request).getUserID();

            commentService.addComment(model,agencyID,userID,commentContent);

            model.addAttribute("agencyID",agencyID);
            return "forward:/agency-detail";

    }

    /*
     * 访问agency-chart页面
     * */

    /*
    * 机构种类数量的扇形图
    * */
    @RequestMapping("/agency-chart-typedata")
    @ResponseBody
    public String agencyTypeChart() throws Exception {
        System.out.println("执行agency-chart-typedata");
        List<Map<String,Integer>> typeList = agencyService.countAgencyType();
        System.out.println(new ObjectMapper().writeValueAsString(typeList));
        return new ObjectMapper().writeValueAsString(typeList);
    }

    /*
     * 机构种类数量的扇形图
     * */
    @RequestMapping("/agency-chart-incomedata")
    @ResponseBody
    public String agencyIncomeChart() throws Exception {
        System.out.println("执行agency-chart-incomedata");
        List<Map<String,Integer>> incomeList = agencyService.countAgencyIncome();
        System.out.println(new ObjectMapper().writeValueAsString(incomeList));
        return new ObjectMapper().writeValueAsString(incomeList);
    }

    /*
     * 机构种类数量的柱形图
     * */
    @RequestMapping("/agency-chart-monthdata")
    @ResponseBody
    public String agencyMonthChart() throws Exception {
        System.out.println("执行agency-chart-monthdata");
        List<Map<String,Integer>> monthList = agencyService.countAgencyMonth();
        System.out.println(new ObjectMapper().writeValueAsString(monthList));
        return new ObjectMapper().writeValueAsString(monthList);
    }

    @RequestMapping("/agency-chart")
    public void info(Model model,HttpServletRequest request) throws Exception {
        String myname = sessionUtils.getSession(request).getUserName();
        model.addAttribute("myname",myname);

        Integer smallNumber=agencyService.countAgencySmall();
        model.addAttribute("smallNumber",smallNumber);

        Integer middleNumber=agencyService.countAgencyMiddle();
        model.addAttribute("middleNumber",middleNumber);

        Integer collegeNumber=agencyService.countAgencyCollege();
        model.addAttribute("collegeNumber",collegeNumber);

        Integer otherNumber=agencyService.countAgencyOther();
        model.addAttribute("otherNumber",otherNumber);

        System.out.println("执行agency-chart");
        /*return "/agency-chart";*/
    }



    /*
    * chenlanyu
    * */

    @RequestMapping("/getPositionMap")
    //@ResponseBody
    public String mapAgency(){
        return "getPositionMap";

    }

    /**
     * 显示添加机构所要填写信息表
     * @return
     */
    @RequestMapping("/addagency")
    //@ResponseBody
    public String addAgency(Model model,HttpServletRequest request){
        String myname = sessionUtils.getSession(request).getUserName();
        model.addAttribute("myname",myname);

        return "add-agency";

    }


    /**
     * 提交添加机构表单
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/submitagency")
    public String submitAgency(
            @RequestParam("agencyName") String agencyName,
            @RequestParam("agencyPhone") String agencyPhone,
            @RequestParam("agencyAddress") String agencyAddress,
            @RequestParam("agencyIncome") String agencyIncome,
            @RequestParam("agencyType") String agencyType,
            @RequestParam("agencyTime") String agencyTime,
            @RequestParam("agencyImg") MultipartFile file,
            @RequestParam("agencyX") String agencyX,
            @RequestParam("agencyY") String agencyY
    ) throws Exception {

//        时间格式转换
        String atime=(String)agencyTime;
        Date date = null;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("yyyy-MM-dd");
        try {
            date = formater.parse(atime);
        } catch (ParseException e) {
            e.printStackTrace();
        }


//      文件上传
        System.out.println(file);

        //获取文件的内容
        InputStream is = file.getInputStream();

        //获取原始文件名
        String originalFilename = file.getOriginalFilename();

        //生成一个uuid名称出来
        String uuidFilename = agencyName+".jpg";

        String myDir =  "D:\\IDEA_workspace\\edu_platform\\src\\main\\resources\\static\\imgs";

        File fileDir = new File(myDir);

        //若文件夹不存在,则创建出文件夹
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        //创建新的文件夹
        File newFile = new File(myDir, uuidFilename);

        //将文件输出到目标的文件中
        file.transferTo(newFile);

        //将保存的文件路径更新到用户信息headimg中
        String savePath = myDir + "/" + uuidFilename;

        System.out.println("生成的图片名字为：" + uuidFilename);
        System.out.println("生成的图片将存到以下地址：" + savePath);

        String imgName = "imgs/" + uuidFilename;


        Agency agency=new Agency(agencyName, agencyAddress, agencyPhone,imgName, agencyType,agencyIncome,date,agencyX,agencyY);
        if(agencyType.equals("幼儿园")){
            agency.setAgencyType("small");
        }else if(agencyType.equals("中小学")){
            agency.setAgencyType("middle");
        }else if (agencyType.equals("大学")){
            agency.setAgencyType("college");
        }else {
            agency.setAgencyType("other");
        }

       /* Agency agency=new Agency(agencyName, agencyAddress, agencyPhone,imgName, agencyType,agencyIncome,date,agencyX,agencyY);*/
        System.out.println(agency.MytoString());



        agencyService.addAgency(agency);
        return "redirect:http://localhost:8080/agencylist";

    }

    @RequestMapping("/searchagency")
    public String searchAgency(){return "agency-list";}


    /*
    * yanranyudi
    * */

    /**
     * 显示更改机构所要填写信息表
     * @return
     */
    @RequestMapping("/updateagency")
    public String updateAgency(Model model,HttpServletRequest request){
        String myname = sessionUtils.getSession(request).getUserName();
        model.addAttribute("myname",myname);
        return "update-agency";
    }

    /**
     * 显示所有机构信息
     * @param map
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/agencylist")
    public String agencyList(@RequestParam Map<String,Object> map ,Model model,HttpServletRequest request) throws Exception {
        System.out.println("list-Controller");
        String myname = sessionUtils.getSession(request).getUserName();
        System.out.println("aaaaaaaaaaaa");
        System.out.println(myname);
        model.addAttribute("myname",myname);
        agencyService.queryAllAgency(model,map);
        return "agency-list";
    }



    /**
     * 提交修改机构表单
     * @return
     * @throws Exception
     */
    @GetMapping("/submitagencyupdate")
    public String updateChoise(Model model) throws Exception {
        System.out.println("choise");
        List<String> agency = agencyService.getAgency();
        System.out.println(agency.get(0));
        model.addAttribute("agency",agency);
        return "update-agency";
    }

    /**
     * 提交表单修改机构信息
     * @param model
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update")
    public String submitAgencyUpdate(Model model,@RequestParam Map<String,Object> map) throws Exception {
        //更新数据
        String agencyTypeC= (String) map.get("agencyType");
        String agencyTypeE=new String();
        if(agencyTypeC.equals("幼儿园")){
            agencyTypeE=("small");
        }else if(agencyTypeC.equals("中小学")){
            agencyTypeE=("middle");
        }else if (agencyTypeC.equals("大学")){
            agencyTypeE=("college");
        }else {
            agencyTypeE=("other");
        }
        agencyService.updateAgency(model,(String)map.get("agencyName"),(String)map.get("agencyNewName"), (String) map.get("agencyPhone"),agencyTypeE, (String) map.get("agencyIncome"));
        return "redirect:http://localhost:8080/agencylist";
    }


    /*public String submitAgencyUpdate(Model model,@RequestParam Map<String,Object> map) throws Exception {
        //更新数据
        agencyService.updateAgency(model,(String)map.get("agencyName"),(String)map.get("agencyNewName"), (String) map.get("agencyPhone"), (String) map.get("agencyType"), (String) map.get("agencyIncome"));
        return "redirect:http://localhost:8080/agencylist";
    }
*/
    /**
     * 删除机构
     * @param model
     * @param ids
     * @return
     */
    @RequestMapping("/deleteAgency")
    public String delete(Model model,@RequestParam("id") String ids) {
        try {
            String nextPath = agencyService.deleteAgency(model,ids);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "redirect:http://localhost:8080/agencylist";
    }


}
