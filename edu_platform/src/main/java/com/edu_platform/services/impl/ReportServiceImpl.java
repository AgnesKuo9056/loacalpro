package com.edu_platform.services.impl;


import com.edu_platform.database.mapper.ReportMapper;
import com.edu_platform.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Resource
    ReportMapper reportMapper;

    @Override
    public List<String> getAgency(){
        return reportMapper.getAgencyName();
    }

    @Override
    public boolean report(String reportContent, Integer userID,String agencyName){
        Integer userid = userID;
        String reportcontent = reportContent;
        Integer agencyid = reportMapper.getAgencyIdByName(agencyName);
        int x = reportMapper.insertReport(reportcontent,userid,agencyid);
        if(x>0){
            return true;
        }else {
            return false;
        }
    }
}
