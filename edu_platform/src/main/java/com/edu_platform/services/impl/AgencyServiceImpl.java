package com.edu_platform.services.impl;

import com.edu_platform.database.data.Agency;
import com.edu_platform.database.mapper.AgencyMapper;
import com.edu_platform.services.AgencyService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AgencyServiceImpl implements AgencyService {

    @Resource
    private AgencyMapper agencyMapper;

    @Override
    public String getAllAgency(Model model) throws Exception{
        List<Agency> list=agencyMapper.getAllAgency();
        model.addAttribute("agencys", list);
        return "agency-table";
    }

    @Override
    public Agency getAgencyByID(Integer agencyID) throws Exception{
        Agency agency=agencyMapper.getAgencyByID(agencyID);
        if(agency==null){
            throw new Exception("获取机构详情失败");
        }
        return agency;
    }

    @Override
    public List<Map<String,Integer>> countAgencyType() throws Exception{
        List<Map<String,Integer>> typeList=agencyMapper.countAgencyType();
        return typeList;
    }

    @Override
    public Integer countAgencySmall() throws Exception{
        Integer smallNumber=agencyMapper.countAgencySmall();
        return smallNumber;
    }

    @Override
    public Integer countAgencyMiddle() throws Exception{
        Integer middleNumber=agencyMapper.countAgencyMiddle();
        return middleNumber;
    }

    @Override
    public Integer countAgencyCollege() throws Exception{
        Integer collegeNumber=agencyMapper.countAgencyCollege();
        return collegeNumber;
    }

    @Override
    public Integer countAgencyOther() throws Exception{
        Integer otherNumber=agencyMapper.countAgencyOther();
        return otherNumber;
    }


    @Override
    public List<Map<String ,Integer>> countAgencyIncome() throws Exception{
        List<Map<String,Integer>> incomeList=agencyMapper.countAgencyIncome();
        return incomeList;
    }

    @Override
    public List<Map<String,Integer>> countAgencyMonth() throws Exception{
        List<Map<String,Integer>> monthList=agencyMapper.countAgencyMonth();
        return monthList;
    }


    /*
    * chenlanyu
    * */
    @Override
    public String addAgency(Agency agency) throws Exception {
        System.out.println(agency.getAgencyAddress());
        agencyMapper.addAgency(agency);
        return "agency-list";

    }

    public List<Map<String,Object>> queryAgencyList(String name) throws Exception {

        return agencyMapper.queryAgencyList(name);

    }



    @Override
    public String queryAllAgency(Model model) throws Exception {
        System.out.println("执行MapService-queryAllAgency");

        List<Agency> agency = agencyMapper.queryAllAgency();
        model.addAttribute("agency",agency);

        System.out.println("地址为："+agency.get(0).getAgencyAddress());

        return "1";
    }

    /**根据agency_id 查询该机构所有信息
     * 返回agency类**/
    @Override
    public String queryAgencyByID(Model model,Integer agencyID) throws Exception {
        System.out.println("执行MapService-queryAgencyByID");
        List<Agency> agency = agencyMapper.queryAgencyByID(agencyID);
        model.addAttribute("agency",agency);

        System.out.println("名字为："+agency.get(0).getAgencyName());
        System.out.println("地址为："+agency.get(0).getAgencyAddress());
        return "";
    }

    /**根据agencyAddress 模糊查询符合情况的机构
     * 返回Agency类**/
    @Override
    public String queryAgencyByName(Model model,String agencyAddress) throws Exception {
        System.out.println("执行MapService-queryAgencyByName");
        List<Agency> agency = agencyMapper.queryAgencyByName(agencyAddress);
        model.addAttribute("agency",agency);

        System.out.println("名字为："+agency);
//        System.out.println("地址为："+agency.get(0).getAgencyAddress());
        return "";
    }

    /**根据agencyType 模糊查询符合情况的机构
     * 返回Agency类**/
    @Override
    public String queryAgencyByType(Model model,String agencyType) throws Exception {
        System.out.println("执行MapService-queryAgencyByType");

        List<Agency> agency = agencyMapper.queryAgencyByType(agencyType);
        model.addAttribute("agency",agency);

        System.out.println("名字为："+agency.get(0).getAgencyName());
        System.out.println("地址为："+agency.get(0).getAgencyAddress());
        return "";
    }

    /** 获取机构数量 **/
    @Override
    public int countAgency() throws Exception {
        System.out.println("执行MapService-countAgency()");
        int num = agencyMapper.countAgency();
        System.out.println("数目为："+ num);
        return num;
    }


    /*
    * yanranyudi
    * */


    /**
     * 按照name查找机构信息
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public List<Agency> queryAgencyListyd(String name) throws Exception {
        return agencyMapper.queryAgencyListyd(name);

    }

    @Override
    public String deleteAgency(Model model, String ids) throws Exception {
        agencyMapper.delete(ids);
        return "";
    }

    @Override
    public String updateAgency(Model model,String agencyName,String agencyNewName, String agencyPhone,  String agencyType, String agencyIncome) throws Exception {
//        Agency agency =new Agency();
        //更新信息
        System.out.println("UPdate serv");
        System.out.println(agencyName);
        agencyMapper.updateAgency(agencyName,agencyNewName,agencyPhone,agencyType,agencyIncome);
        return "";
    }

    @Override
    public String quary(Model model, String agencyName) throws Exception {
        return null;
    }

    @Override
    public String queryAllAgency(Model model, Map<String,Object> map) throws Exception {
//        System.out.println(agencyName);
        List<Agency> agency;
        if(map.get("agencyName") == null){
            agency = agencyMapper.queryAllAgency();
            System.out.println("kong!");

        }else{
            agency = agencyMapper.queryAgencyByNameyd(String.valueOf(map.get("agencyName")));
            System.out.println("执行Service-queryName");
        }
        model.addAttribute("agency",agency);
        System.out.println("返回参数");
        return "";
    }

    @Override
    public List<String> getAgency() throws Exception {
        return agencyMapper.getAgency();
    }
}
