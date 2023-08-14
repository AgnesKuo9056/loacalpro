package com.edu_platform.database.mapper;

import com.edu_platform.database.data.*;
import com.edu_platform.database.data.Agency;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author ChenLanyu
 * @CreateTime 2022/2/28 8:56
 **/
@Mapper
public interface IMap {


    /**查询所有机构
     * 返回agency类**/
    @Select("select * from agency")
    public List<Agency> queryAllAgency() throws Exception;

    /**根据agency_id 查询该机构所有信息
     * 返回agency类**/
    @Select("select * from agency where agency_id = #{agencyID}")
    public List<Agency> queryAgencyByID(Integer agencyID) throws Exception;

    /**根据agencyName 模糊查询符合情况的机构
     * 返回Agency类**/
    @Select("<script>"
            + "select * from agency"
            +   "<where>"
            +       "<if test='agency_name!=null'>"
            +       "agency_name like '%${agencyName}%'"
            +       "</if>"
            +   "</where>"  //为什么用where标签？ 避免表中为空时查询不成立
            +"</script>")
    public List<Agency> queryAgencyByName(String agencyName) throws Exception;

    /**根据agencyType 模糊查询符合情况的机构
     * 返回Agency类**/
    @Select("select * from agency where agency_type = #{agencyType}")
    public List<Agency> queryAgencyByType(String agencyType) throws Exception;

    /** 获取机构数量 **/
    @Select("select count(*) from agency")
    public int countAgency()throws Exception;



}
