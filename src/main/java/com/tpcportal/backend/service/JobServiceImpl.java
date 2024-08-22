package com.tpcportal.backend.service;

import com.tpcportal.backend.dto.JobDTO;
import com.tpcportal.backend.entity.EligibilityCriteria;
import com.tpcportal.backend.entity.Job;
import com.tpcportal.backend.entity.RecruitmentRound;
import com.tpcportal.backend.entity.UserData;
import com.tpcportal.backend.repository.JobFetchRepository;
import com.tpcportal.backend.repository.JobRepository;
import com.tpcportal.backend.repository.UserDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobServiceImpl implements JobService{

    @Autowired
    private JobFetchRepository jobFetchRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Override
    public List<Job> findJobByEligibilityCriteria(int gradYear, String degree, String branch, BigDecimal cpi, Integer gender, String rollNo) {
        return jobFetchRepository.findJobByEligibilityCriteria(gradYear,degree,branch,cpi,gender,rollNo);
    }

    @Override
    public List<Job> findJobsByUserApplications(String rollNo) {
        System.out.println("Debug 12");
        return jobFetchRepository.findJobsByUserApplications(rollNo);
    }

    @Override
    public List<Job> findQualifiedJobsByUser(String rollNo) {
        return jobFetchRepository.findQualifiedJobsByUser(rollNo);
    }

    @Override
    @Transactional
    public boolean addJob(JobDTO jobDTO) {
        try {
            //fetch the user details corresponding to the jobDTO.getPhRollNo()
            UserData userData = userDataRepository.findByRollNo(jobDTO.getPhRollNo());

            if (userData == null) {
                // Handle the case where the user is not found
                System.out.println("User not found with RollNo: " + jobDTO.getPhRollNo());
                return false;
            }
            //make a new job instance
            Job job = new Job(jobDTO.getJobId(), jobDTO.getCompanyName(), jobDTO.getCompanyDetails(), jobDTO.getJobType(), jobDTO.getRole(), jobDTO.getPkg(), jobDTO.getStipend(), jobDTO.getLocation(), jobDTO.getDuration(), jobDTO.getDeadline(), jobDTO.getPhMemberName(), jobDTO.getPhoneNumberStud(), jobDTO.getInstiMail(), jobDTO.getJobDescription(), LocalDateTime.now(), LocalDateTime.now());
            List<EligibilityCriteria> eligibilityCriterias = jobDTO.createEligibilityCriterias();
            List<RecruitmentRound> recruitmentRounds = jobDTO.createRecruitmentRounds();
            for(EligibilityCriteria eligibilityCriteria : eligibilityCriterias){
                job.addEligibilityCriterias(eligibilityCriteria);
            }
            for(RecruitmentRound recruitmentRound : recruitmentRounds){
                job.addRecruitmentRounds(recruitmentRound);
            }
            job.setUserData(userData);
            //associate the user details to the job

            //save the job
            jobRepository.save(job);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean updateJob(JobDTO jobDTO) {
        try {
            // Fetch the job using the job ID from jobDTO
            Job job = jobRepository.findByJobId(jobDTO.getJobId());
            if (job == null) {
                System.out.println("Job not found with jobId: " + jobDTO.getJobId());
                return false;
            }

            // Fetch the user details corresponding to the jobDTO.getPhRollNo()
            UserData userData = userDataRepository.findByRollNo(jobDTO.getPhRollNo());
            if (userData == null) {
                System.out.println("User not found with phRollNo: " + jobDTO.getPhRollNo());
                return false;
            }

            job.setCompanyName(jobDTO.getCompanyName());
            job.setCompanyDetails(jobDTO.getCompanyDetails());
            job.setJobType(jobDTO.getJobType());
            job.setRole(jobDTO.getRole());
            job.setPkg(jobDTO.getPkg());
            job.setStipend(jobDTO.getStipend());
            job.setLocation(jobDTO.getLocation());
            job.setDuration(jobDTO.getDuration());
            job.setDeadline(jobDTO.getDeadline());
            job.setPhMemberName(jobDTO.getPhMemberName());
            job.setPhoneNumberStud(jobDTO.getPhoneNumberStud());
            job.setInstiMail(jobDTO.getInstiMail());
            job.setJobDescription(jobDTO.getJobDescription());
            job.setUserData(userData);

//            job.setEligibilityCriterias(jobDTO.createEligibilityCriterias());
//            job.setRecruitmentRounds(jobDTO.createRecruitmentRounds());
            job.clearEligibilityCriterias();
            job.clearRecruitmentRounds();
            System.out.println(job.getEligibilityCriterias());
            System.out.println(job.getRecruitmentRounds());

            List<EligibilityCriteria> eligibilityCriterias = jobDTO.createEligibilityCriterias();
            List<RecruitmentRound> recruitmentRounds = jobDTO.createRecruitmentRounds();
            for(EligibilityCriteria eligibilityCriteria : eligibilityCriterias){
                job.addEligibilityCriterias(eligibilityCriteria);
            }
            for(RecruitmentRound recruitmentRound : recruitmentRounds){
                job.addRecruitmentRounds(recruitmentRound);
            }

//            job.setEligibilityCriterias(jobDTO.createEligibilityCriterias());
//            job.setRecruitmentRounds(jobDTO.createRecruitmentRounds());

            job.setUpdatedAt(LocalDateTime.now());
            jobRepository.save(job);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Job> findJobsInProcess(String rollNo) {
        return jobFetchRepository.findJobsInProcess(rollNo);
    }

    @Override
    public List<Job> findCompletedJobs(String rollNo) {
        return jobFetchRepository.findCompletedJobsForPhMember(rollNo);
    }


}
