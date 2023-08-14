package com.edu_platform.database.mapper;

import com.edu_platform.database.data.Agency;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface AgencyMapper {

    /**
     *普通用户查看所有的机构
     */
    @Select("select agency_name,agency_type,agency_address,agency_id from agency order by agency_id asc")
    public List<Agency> getAllAgency() throws Exception;

    /*
    * 用户通过机构的id查看该机构的详情
    */
    @Select("Select * from agency where agency_id=#{agencyID}")
    public Agency getAgencyByID(Integer agencyID) throws Exception;

    /*
    * 查询机构种类的数量
    * */
    @Select("select agency_type type,count(agency_id) number from agency group by agency_type ")
    public List<Map<String,Integer>> countAgencyType() throws Exception;

    /*
     * 查询机构种类为幼儿园的数量
     * */
    @Select("select count(agency_id) number from agency where agency_type='small' ")
    public Integer countAgencySmall() throws Exception;

    @Select("select count(agency_id) number from agency where agency_type='middle' ")
    public Integer countAgencyMiddle() throws Exception;

    @Select("select count(agency_id) number from agency where agency_type='college' ")
    public Integer countAgencyCollege() throws Exception;

    @Select("select count(agency_id) number from agency where agency_type='other' ")
    public Integer countAgencyOther() throws Exception;

    /*
    * 查询机构营利性情况
    * */
    @Select("select agency_income income,count(agency_id) number from agency group by agency_income")
    public List<Map<String,Integer>> countAgencyIncome() throws Exception;

    /*
    * 查询机构成立年份信息
    * */
    @Select("select month(agency_time) as month,count(agency_id) as number from agency where year(agency_time)=2022 group by month (agency_time)")
    public List<Map<String ,Integer>> countAgencyMonth() throws Exception;


//chenlanyu
    /**
     * 增加机构信息(已更加XY、IMG）
     * @param agency
     * @throws Exception
     */
    @Insert("insert into agency(agency_name,agency_address,agency_phone,agency_img,agency_type,agency_income,agency_time,agency_x,agency_y)values(#{agencyName},#{agencyAddress},#{agencyPhone},#{agencyImg}, #{agencyType},#{agencyIncome},#{agencyTime},#{agencyX},#{agencyY})")
    public void addAgency(Agency agency)throws Exception;


    /**
     * 按照名称模糊查询机构
     * @param agencyName
     * @return
     * @throws Exception
     */
    @Select("<script>"
            +  "select * from agency"
            +    "<where>"
            +      "<if test='agencyName!=null'>"
            +        "agency_name like '%${agencyName}%'"
            +      "</if>"
            +  "</where>"
            +"</script>")
    public List<Agency> queryAll(String agencyName)throws Exception;

    /**
     * 按照名称模糊查询机构
     * @param agencyName
     * @return
     * @throws Exception
     */
    @Select("<script>"
            +  "select agency_name,agency_address,agency_phone,agency_type,agency_income,agency_time from agency"
            +    "<where>"
            +      "<if test='agencyName!=null'>"
            +        "agency_name like '%${agencyName}%'"
            +      "</if>"
            +  "</where>"
            +"</script>")
    public List<Map<String,Object>> queryAgencyList(String agencyName)throws Exception;



    /**查询所有机构
     * 返回agency类**/
    @Select("select * from agency")
    public List<Agency> queryAllAgency() throws Exception;

    /**根据agency_id 查询该机构所有信息
     * 返回agency类**/
    @Select("select * from agency where agency_id = #{agencyID}")
    public List<Agency> queryAgencyByID(Integer agencyID) throws Exception;


    /**根据agencyAddress 模糊查询符合情况的机构
     * 返回Agency类**/
    @Select("<script>"
            + "select * from agency"
            +   "<where>"
            +       "<if test='agency_address!=null'>"
            +       "agency_address like '%${agencyAddress}%'"
            +       "</if>"
            +   "</where>"  //为什么用where标签？ 避免表中为空时查询不成立
            +"</script>")
    public List<Agency> queryAgencyByName(String agencyAddress) throws Exception;



    /**根据agencyType 模糊查询符合情况的机构
     * 返回Agency类**/
    @Select("select * from agency where agency_type = # {agencyType}")
    public List<Agency> queryAgencyByType(String agencyType) throws Exception;

    /** 获取机构数量 **/
    @Select("select count(*) from agency")
    public int countAgency()throws Exception;



    /*
    * yanranyudi""""""""""""""""""""""""""""
    * */



 /**
  * 更改机构信息(名称、联系方式、类型、营利）
  * @param agencyName
  * @param agencyNewName
  * @param agencyType
  * @param agencyIncome
  * @throws Exception
  */
 @Update("update agency set agency_name=#{agencyNewName},agency_phone=#{agencyPhone},agency_type=#{agencyType},agency_income=#{agencyIncome} where  agency_name=#{agencyName}")
 public void updateAgency(String agencyName,String agencyNewName, String agencyPhone, String agencyType, String agencyIncome)throws Exception;



 /**
  * 按照名称模糊查询机构
  * @param agencyName
  * @return
  * @throws Exception
  */
 @Select("<script>"
         +  "select agency_id,agency_name,agency_address,agency_phone,agency_type,agency_income,agency_time from agency"
         +    "<where>"
         +      "<if test='agencyName!=null'>"
         +        "agency_name like '%${agencyName}%'"
         +      "</if>"
         +  "</where>"
         +"</script>")
 public List<Agency> queryAgencyListyd(String agencyName)throws Exception;



 /**
  * 删除机构
  * @param ids
  * @throws Exception
  */
 @Delete("delete from agency where agency_id in(${ids})")
 public void delete(String ids) throws Exception;

/* *//**查询所有机构
  * 返回agency类**//*
 @Select("select * from agency")
 public List<Agency> queryAllAgency() throws Exception;*/

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
 public List<Agency> queryAgencyByNameyd(String agencyName) throws Exception;


 @Select("select agency_name from agency")
 List<String> getAgency()throws Exception;
}
