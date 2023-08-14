package com.edu_platform.database.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReportMapper {

    @Select("Select agency_name from agency")
    public List<String> getAgencyName();

    @Insert("insert into report(report_content,user_id,agency_id) values(#{reportContent},#{userID},#{agencyID})")
    public int insertReport(String reportContent, Integer userID, Integer agencyID);

    @Select("Select agency_id from agency where agency_name=#{agencyName}")
    public Integer getAgencyIdByName(String agencyName);
}
