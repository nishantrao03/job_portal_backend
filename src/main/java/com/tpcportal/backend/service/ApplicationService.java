package com.tpcportal.backend.service;

import com.tpcportal.backend.dto.ApplicationDTO;
import com.tpcportal.backend.dto.StudentDetailsDTO;
import com.tpcportal.backend.entity.Application;

import java.util.List;

public interface ApplicationService {
    boolean addApplication(ApplicationDTO applicationDTO);
    boolean deleteApplication(String rollNo, String jobId);
    List<StudentDetailsDTO> getQualifiedStudents(String jobId, String rollNo);
    boolean updateRoundsClearedAndSelection(List<String> rollNos, String jobId, String rollNo);
    Application getApplicationById(String rollNo, String jobId);
}
