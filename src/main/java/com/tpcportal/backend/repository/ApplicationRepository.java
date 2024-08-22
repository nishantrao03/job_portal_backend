package com.tpcportal.backend.repository;

import com.tpcportal.backend.dto.StudentDetailsDTO;
import com.tpcportal.backend.embeddedids.ApplicationId;
import com.tpcportal.backend.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, ApplicationId> {
    Application findApplicationById(ApplicationId applicationId);
    @Query("SELECT new com.tpcportal.backend.dto.StudentDetailsDTO(uc.rollno, uc.instiMail, ud.firstName, ud.middleName, ud.lastName) " +
            "FROM Application a " +
            "JOIN a.userData ud " +
            "JOIN ud.userCredentials uc " +
            "JOIN a.job j " +
            "WHERE j.jobId = :jobId " +
            "AND j.completedRounds = a.roundsCleared")
    List<StudentDetailsDTO> findEligibleStudentsForNextRound(@Param("jobId") String jobId);
}
