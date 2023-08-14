package com.edu_platform.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReportService {

    public List<String> getAgency();

    public boolean report(String reportContent, Integer userID,String agencyName);

}
