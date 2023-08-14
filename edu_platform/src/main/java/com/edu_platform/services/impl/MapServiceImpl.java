package com.edu_platform.services.impl;

import com.edu_platform.database.data.Agency;
import com.edu_platform.database.mapper.IMap;
import com.edu_platform.services.IMapService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author ChenLanyu
 * @CreateTime 2022/2/28 8:52
 **/
@Service
public class MapServiceImpl implements IMapService {

    @Resource
    private IMap iMap;



    @Override
    public String queryAllAgency(Model model,String agencyName) throws Exception {
        System.out.println("执行MapService-queryAllAgency");
        List<Agency> agency;
        String agencySearchName = agencyName;
        int agencySNum=0;

//        判断输入的agencyName是否为空
        if("".equals(agencyName)){
            agency = iMap.queryAllAgency();
            agencySNum=agency.size();
        }else{
            agency =  iMap.queryAgencyByName(agencyName);
            agencySNum=agency.size();
        }

        System.out.println("获取的agencyNum是："+ agencySNum);

        model.addAttribute("agency",agency);
        model.addAttribute("agencySearchName",agencySearchName);
        model.addAttribute("agencySNum",agencySNum);


        return "";
    }

    /**根据agency_id 查询该机构所有信息
     * 返回agency类**/
    @Override
    public String queryAgencyByID(Model model,Integer agencyID) throws Exception {
        System.out.println("执行MapService-queryAgencyByID");
        List<Agency> agency = iMap.queryAgencyByID(agencyID);
        model.addAttribute("agency",agency);


        return "";
    }

    /**根据agencyName 模糊查询符合情况的机构
     * 返回Agency类**/
    @Override
    public List<Agency> queryAgencyByName(String agencyName) throws Exception {
        System.out.println("执行MapService-queryAgencyByName");
        List<Agency> agencyList = iMap.queryAgencyByName(agencyName);
//        model.addAttribute("agency",agencyList);
//        System.out.println(model.getAttribute("agency"));
        return agencyList;
    }

    /**根据agencyType 模糊查询符合情况的机构
     * 返回Agency类**/
    @Override
    public String queryAgencyByType(Model model,String agencyType) throws Exception {
        System.out.println("执行MapService-queryAgencyByType");

        List<Agency> agency = iMap.queryAgencyByType(agencyType);

        switch (agencyType) {
            case "small":
                model.addAttribute("agencySmall", agency);
                System.out.println(agency.get(0).getAgencyID());
                int smallnum=agency.size();
                model.addAttribute("smallNum",smallnum);
                break;
            case "middle":
                model.addAttribute("agencyMiddle", agency);
                int middleNum=agency.size();
                model.addAttribute("middleNum",middleNum);
                break;
            case "college":
                model.addAttribute("agencyCollege", agency);
                int collegeNum=agency.size();
                model.addAttribute("collegeNum",collegeNum);
                break;
            case "other":
                model.addAttribute("agencyOther", agency);
                int otherNum=agency.size();
                model.addAttribute("otherNum",otherNum);
                break;
            default:
                System.out.println("errorType in serviceTypeQuery");
                break;
        }

        return "";
    }

    /** 获取机构数量 **/
    @Override
    public int countAgency(Model model) throws Exception {
        System.out.println("执行MapService-countAgency()");
        int num = iMap.countAgency();
        model.addAttribute("agencyNum",num);
        System.out.println("数目为："+ num);
        return num;
    }
}
