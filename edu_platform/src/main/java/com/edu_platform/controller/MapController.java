package com.edu_platform.controller;

import com.edu_platform.utils.SessionUtils;
import com.edu_platform.database.data.Agency;
import com.edu_platform.services.IMapService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;



/**
 * @Author ChenLanyu
 * @CreateTime 2022/2/28 8:49
 **/

@Controller
@SessionAttributes({"test"})
public class MapController {
    SessionUtils sessionUtils;
    @Resource
    private IMapService iMapService;

//    在map.html页面直接查询所有agency
    @RequestMapping("/map")
    public void mapAll(Model model, String agencyName, HttpServletRequest request) throws Exception{
        String myname = sessionUtils.getSession(request).getUserName();
        model.addAttribute("myname",myname);
        System.out.println("执行 mapcontroller-mapAll");
        iMapService.queryAllAgency(model,agencyName);
        iMapService.queryAgencyByType(model,"small");
        iMapService.queryAgencyByType(model,"middle");
        iMapService.queryAgencyByType(model,"college");
        iMapService.queryAgencyByType(model,"other");
        iMapService.countAgency(model);

    }

//    按照agency名字搜索
//    @ResponseBody
    @RequestMapping("/map/queryName")
    @ResponseBody
    public String mapName(Model model, @RequestParam("agencyName")String agencyName, HttpServletResponse response) throws Exception{

        List<Agency> agencyList =  iMapService.queryAgencyByName(agencyName);
        System.out.println("---执行 mapcontroller-mapName---");
        System.out.println("前端获取到的agencyName:"+ agencyName);

//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("agencyList", agencyList);
//            System.out.println(agencyList.get(0).getAgencyY());
//            System.out.println(agencyList.get(0).getAgencyX());
//        }catch (JSONException e){
//            e.printStackTrace();
//        }

        return new ObjectMapper().writeValueAsString(agencyList);
//
//        response.setContentType("application/json; charset=UTF-8");
//        PrintWriter writer = response.getWriter();
//        writer.print(jsonObject);
//        writer.flush();
//        writer.close();


    }


}
