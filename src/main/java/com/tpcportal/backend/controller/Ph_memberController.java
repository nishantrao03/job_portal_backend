package com.tpcportal.backend.controller;

import com.tpcportal.backend.dto.JobDTO;
import com.tpcportal.backend.dto.StudentDetailsDTO;
import com.tpcportal.backend.dto.UpdateRoundsClearedDTO;
import com.tpcportal.backend.entity.Job;
import com.tpcportal.backend.repository.JobRepository;
import com.tpcportal.backend.service.ApplicationService;
import com.tpcportal.backend.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ph_member")
public class Ph_memberController {

    @Autowired
    private JobService jobService;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/hello")
    public String sayHello(){
        return "hello";
    }

    @PostMapping("/add-job")
    public ResponseEntity<?> addJob(@RequestBody JobDTO jobDTO){
        boolean isAdded = jobService.addJob(jobDTO);
        if (isAdded) {
            return ResponseEntity.ok("Job added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add job");
        }
    }

//    @PostMapping("/update-job")
//    public ResponseEntity<?> updateJob(@RequestBody JobDTO jobDTO){
//        System.out.println("Entered debug");
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("Authenticated user: " + auth.getName());
//        System.out.println("Authorities: " + auth.getAuthorities());
//        boolean isAdded = jobService.updateJob(jobDTO);
//        if (isAdded) {
//            return ResponseEntity.ok("Job updated successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update job");
//        }
//    }

    @PostMapping("/update-job")
    public ResponseEntity<?> updateJob(@RequestBody JobDTO jobDTO){
        System.out.println("Entered debug");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authenticated user: " + auth.getName());
        System.out.println("Authorities: " + auth.getAuthorities());
        boolean isAdded = jobService.updateJob(jobDTO);
        if (isAdded) {
            return ResponseEntity.ok("Job updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update job");
        }
    }

    @GetMapping("/get-qualified-students")
    public ResponseEntity<?> getQualifiedStudents(@RequestParam String jobId, @RequestParam String rollNo) {
        try {
            List<StudentDetailsDTO> studentDetailsDTOList = applicationService.getQualifiedStudents(jobId, rollNo);

            if (studentDetailsDTOList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No qualified students found for the given job ID.");
            }

            return ResponseEntity.ok(studentDetailsDTOList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid job ID provided.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while retrieving the qualified students.");
        }
    }

    @PostMapping("/update-qualified-students")
    public ResponseEntity<?> updateQualificationStatus(@RequestBody UpdateRoundsClearedDTO updateRoundsClearedDTO){
        boolean result = applicationService.updateRoundsClearedAndSelection(updateRoundsClearedDTO.getRollNos(), updateRoundsClearedDTO.getJobId(), updateRoundsClearedDTO.getRollNo());
        System.out.println(result);
        if (result) {
            return ResponseEntity.ok("Rounds cleared and selection updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating the rounds cleared and selection.");
        }

    }

    @GetMapping("/get-in-process-jobs")
    public ResponseEntity<?> getJobsInProcess(@RequestParam String rollNo){
        try {
            List<Job> jobsInProcess = jobService.findJobsInProcess(rollNo);
            if (jobsInProcess.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No in-process jobs found for the given roll number.");
            }
            return ResponseEntity.ok(jobsInProcess);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching in-process jobs.");
        }
    }

    @GetMapping("/get-completed-jobs")
    public ResponseEntity<?> getCompletedJobs(@RequestParam String rollNo){
        try {
            List<Job> jobsInProcess = jobService.findCompletedJobs(rollNo);
            if (jobsInProcess.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No in-process jobs found for the given roll number.");
            }
            return ResponseEntity.ok(jobsInProcess);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching in-process jobs.");
        }
    }
}
