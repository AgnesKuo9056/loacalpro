package com.edu_platform.services;

import com.edu_platform.database.data.Agency;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public interface AgencyService {

    /*
    * 用户查询所有的机构信息
    * */
    public String getAllAgency(Model model) throws Exception;

    /*
    * 根据机构的ID查看机构详情
    * */
    public Agency getAgencyByID(Integer agencyID) throws Exception;

    /*
     * 查询机构种类的数量
     * */
    public List<Map<String,Integer>> countAgencyType() throws Exception;

    /*
     * 查询机构种类为幼儿园的数量
     * */
    public Integer countAgencySmall() throws Exception;

    public Integer countAgencyMiddle() throws Exception;

    public Integer countAgencyCollege() throws Exception;

    public Integer countAgencyOther() throws Exception;

    /*
    * 查询机构营利性情况
    * */
    public List<Map<String ,Integer>> countAgencyIncome() throws Exception;

    /*
    * 查询机构本年各月份得成立数量
    * */
    public List<Map<String,Integer>> countAgencyMonth() throws Exception;


    /*
    * chenlanyu
    * */


    /**
     * 获取机构列表
     * @param name
     * @return
     * @throws Exception
     */
    List<Map<String,Object>> queryAgencyList(String name) throws Exception;


    /**
     * 删除机构
     * @param model
     * @param ids
     * @throws Exception
     */
    public String deleteAgency(Model model, String ids)throws Exception;


    public String queryAllAgency(Model model)throws Exception;

    /**根据agency_id 查询该机构所有信息
     * 返回agency类**/
    public String queryAgencyByID(Model model, Integer agencyID) throws Exception;

    /**根据agencyAddress 模糊查询符合情况的机构
     * 返回Agency类**/
    public String queryAgencyByName(Model model, String agencyAddress) throws Exception;

    /**根据agencyType 模糊查询符合情况的机构
     * 返回Agency类**/
    public String queryAgencyByType(Model model, String agencyType) throws Exception;

    /** 获取机构数量 **/
    public int countAgency()throws Exception;



    /*
    * YANRANYUDI
    * */
    /**
     * @Author YanRanyudi
     * @CreateTime 2022/2/28 9:27
     * @Version 1.0.0
     */
        /**
         * 增加机构
         * @param agency
         * @return
         * @throws Exception
         */
        public String addAgency(Agency agency) throws Exception;

        /**
         * 获取机构列表
         * @param name
         * @return
         * @throws Exception
         */
        List<Agency> queryAgencyListyd(String name) throws Exception;




        /**
         * 更改机构信息(名称、联系方式、类型、营利）
         * @param model
         * @param agencyName
         * @param agencyPhone
         * @param agencyType
         * @param agencyIncome
         * @return
         * @throws Exception
         */
        public String updateAgency(Model model, String agencyName,String agencyNewName, String agencyPhone,String agencyType, String agencyIncome)throws Exception;

        /**
         * 按名称查找机构
         * @param model
         * @param agencyName
         * @return
         * @throws Exception
         */
        public String quary(Model model, String agencyName) throws Exception;

        public String queryAllAgency(Model model,Map<String,Object> map)throws Exception;


        public List<String> getAgency()throws Exception;

}
