package com.tpcportal.backend.controller;

import com.tpcportal.backend.dto.ApplicationDTO;
import com.tpcportal.backend.dto.ClientUserDTO;
import com.tpcportal.backend.dto.PasswordChangeDTO;
import com.tpcportal.backend.dto.UserDTO;
import com.tpcportal.backend.entity.Application;
import com.tpcportal.backend.entity.Job;
import com.tpcportal.backend.service.ApplicationService;
import com.tpcportal.backend.service.JobService;
import com.tpcportal.backend.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private JobService jobService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/hello")
    public String sayHello(){
        return "Welcome student";
    }

    @GetMapping("/eligible-jobs")
    public ResponseEntity<?> getEligibleJobs(@RequestParam int gradYear,
                                                     @RequestParam String degree,
                                                     @RequestParam String branch,
                                                     @RequestParam BigDecimal cpi,
                                                     @RequestParam Integer gender,
                                                     @RequestParam String rollNo) {
        try {
            System.out.println("Fetching eligible jobs");
            List<Job> jobs = jobService.findJobByEligibilityCriteria(gradYear, degree, branch, cpi, gender, rollNo);

            if (jobs.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No jobs found for the provided criteria.");
            }
            return ResponseEntity.ok(jobs);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid parameters provided", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
        }
    }

    @GetMapping("/applied-jobs")
    public ResponseEntity<?> getAppliedJobs(@RequestParam String rollNo) {
        try {
            List<Job> jobs = jobService.findJobsByUserApplications(rollNo);
            if (jobs.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No jobs applied by the user with roll number " + rollNo);
            }

            return ResponseEntity.ok(jobs);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid parameters provided: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/qualified-jobs")
    public ResponseEntity<?> getQualifiedJobs(@RequestParam String rollNo) {
        try {
            List<Job> jobs = jobService.findQualifiedJobsByUser(rollNo);
            if (jobs.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No jobs qualified by the user with roll number " + rollNo);
            }

            return ResponseEntity.ok(jobs);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid parameters provided: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/submit-application")
    public ResponseEntity<?> submitApplication(@RequestBody ApplicationDTO applicationDTO){
        boolean isAdded = applicationService.addApplication(applicationDTO);
        if (isAdded) {
            return ResponseEntity.ok("Application submitted successfully");
        } else {
            return ResponseEntity.status(500).body("Failed to submit application");
        }
    }

    @PostMapping("/edit-profile")
    public ResponseEntity<?> editProfile(@RequestBody UserDTO userDTO) {
        //make sure to send password value null from frontend

        //do this data transfer from UserDTO to ClientUserDTO in frontend

//        clientUserDTO.setCpi(userDTO.getCpi());
//        clientUserDTO.setBranch(userDTO.getBranch());
//        clientUserDTO.setDegree(userDTO.getDegree());
//        clientUserDTO.setDob(userDTO.getDob());
//        clientUserDTO.setGender(userDTO.getGender());
//        clientUserDTO.setFirstName(userDTO.getFirstName());
//        clientUserDTO.setMiddleName(userDTO.getMiddleName());
//        clientUserDTO.setLastName(userDTO.getLastName());
//        clientUserDTO.setLinkedin(userDTO.getLinkedin());
//        clientUserDTO.setPersonalMail(userDTO.getPersonalMail());
//        clientUserDTO.setPhoneNumber(userDTO.getPhoneNumber());
//        clientUserDTO.setResume(userDTO.getResume());
//        clientUserDTO.setTenthPercentage(userDTO.getTenthPercentage());
//        clientUserDTO.setTwelfthOrDiplomaPercentage(userDTO.getTwelfthOrDiplomaPercentage());

        try {
            System.out.println("Debug again");
            boolean success = registrationService.editProfile(userDTO);
            System.out.println(success);
            if (success) {
                return ResponseEntity.ok("Profile updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update profile");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating profile: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete-application")
    public ResponseEntity<?> deleteApplication(@RequestParam String rollNo, @RequestParam String jobId){
        System.out.println("Debug j");
        boolean isDeleted = applicationService.deleteApplication(rollNo, jobId);

        if (isDeleted) {
            return ResponseEntity.ok("Application deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Application not found.");
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeDTO passwordChangeDTO){
        try {
            boolean isChanged = registrationService.changePassword(passwordChangeDTO.getRollNo(), passwordChangeDTO.getOldPassword(), passwordChangeDTO.getNewPassword());
            if (isChanged) {
                return ResponseEntity.ok("Password changed successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to change password");
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while changing the password");
        }

    }

    @GetMapping("/get-application")
    public ResponseEntity<Integer> getApplication(@RequestParam String rollNo, @RequestParam String jobId) {
        try {
            Application application = applicationService.getApplicationById(rollNo, jobId);

            // Check if application is null (additional safety check)
            if (application == null) {
                return ResponseEntity.ok(0);  // Returning 0 if the application is not found
            }

            return ResponseEntity.ok(application.getRoundsCleared());

        } catch (Exception e) {
            // Handle any exception that occurs and return 0
            System.out.println("Error fetching application: " + e.getMessage());
            return ResponseEntity.ok(0);
        }
    }

}
