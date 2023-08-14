package com.edu_platform.services;

import com.edu_platform.database.data.Agency;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

/**
 * @Author ChenLanyu
 * @CreateTime 2022/2/28 8:52
 **/
@Service
public interface IMapService {

    public String queryAllAgency(Model model,String agencyName)throws Exception;

    /**根据agency_id 查询该机构所有信息
     * 返回agency类**/
    public String queryAgencyByID(Model model,Integer agencyID) throws Exception;

    /**根据agencyName 模糊查询符合情况的机构
     * 返回Agency类**/
    public List<Agency> queryAgencyByName(String agencyName) throws Exception;

    /**根据agencyType 模糊查询符合情况的机构
     * 返回Agency类**/
    public String queryAgencyByType(Model model,String agencyType) throws Exception;

    /** 获取机构数量 **/
    public int countAgency(Model model)throws Exception;
}
