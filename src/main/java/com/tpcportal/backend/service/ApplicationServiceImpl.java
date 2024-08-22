package com.tpcportal.backend.service;

import com.tpcportal.backend.dto.ApplicationDTO;
import com.tpcportal.backend.dto.StudentDetailsDTO;
import com.tpcportal.backend.embeddedids.ApplicationId;
import com.tpcportal.backend.entity.*;
import com.tpcportal.backend.repository.ApplicationRepository;
import com.tpcportal.backend.repository.JobRepository;
import com.tpcportal.backend.repository.UserDataRepository;
import com.tpcportal.backend.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService{
    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    @Transactional
    public boolean addApplication(ApplicationDTO applicationDTO) {
        try{
            //declare application object
            Application application = new Application();
            //fetch user and job details
            UserCredentials userCredentials = userRepository.findByRollno(applicationDTO.getRollNo());
            application.setUserData(userCredentials.getUserData());
            Job job = jobRepository.findByJobId(applicationDTO.getJobId());
            application.setJob(job);
            // Check if the application deadline has passed
            if (job.getDeadline().isBefore(LocalDate.now())) {
                System.out.println("Debug: Application deadline has passed.");
                return false;
            }
            //System.out.println();

            UserData userData = userCredentials.getUserData();

            // Check if the CPI meets the eligibility criteria
            boolean isEligible = job.getEligibilityCriterias().stream().anyMatch(criteria ->
                    (criteria.getGender() == 2 || criteria.getGender().equals(userData.getGender())) &&
                            criteria.getId().getDegree().equals(userData.getDegree()) &&
                            criteria.getId().getBranch().equals(userData.getBranch()) &&
                            (criteria.getCpiCutoff() == null || criteria.getCpiCutoff().compareTo(applicationDTO.getCpi()) <= 0)
            );
            System.out.println("Am i being consoled?");
            System.out.println(isEligible);

            if (!isEligible) {
                System.out.println("Am I being reached?");
                System.out.println("Debug: User does not meet the eligibility criteria.");
                return false;
            }

            //create and set ApplicationId
            ApplicationId applicationId = new ApplicationId(userData.getRollNo(), job.getJobId());
            application.setId(applicationId);
            //fill details
            application.setPersonalMail(applicationDTO.getPersonalMail());
            application.setPhoneNumber(applicationDTO.getPhoneNumber());
            application.setCpi(applicationDTO.getCpi());
            application.setResume(applicationDTO.getResume());
            application.setLinkedin(applicationDTO.getLinkedIn());
            application.setCoverLetter(applicationDTO.getCoverLetter());
            application.setCreatedAt(LocalDateTime.now());
            application.setUpdatedAt(LocalDateTime.now());
            System.out.println("Debug mai pahuch gaya");
            //console the application
            //System.out.println(application.toString());
            //finally save the application

            applicationRepository.save(application);
            return true;
        } catch (Exception e) {
            System.out.println("Debug catch 1");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteApplication(String rollNo, String jobId){
        try{
            ApplicationId applicationId = new ApplicationId(rollNo, jobId);
            Application myApplication = applicationRepository.findApplicationById(applicationId);
//            UserCredentials userCredentials = userRepository.findByRollno(rollNo);
//            List<Application> applications = userCredentials.getUserData().getApplications();
//            Application myApplication = new Application();
//            for(Application application : applications){
//                if(application.getId().getJobId()==jobId){
//                    myApplication = application;
//                    break;
//                }
//            }
            if (myApplication != null) {
                //applicationRepository.deleteById(applicationId);
                System.out.println(myApplication);
                applicationRepository.deleteById(applicationId);
                return true;
            } else {
                System.out.println("Application not found with the given ID.");
                return false;
            }
        } catch (Exception exc){
            exc.printStackTrace();
            return false;
        }
    }

    @Override
    public List<StudentDetailsDTO> getQualifiedStudents(String jobId, String rollNo) {
        try{
            Job job = jobRepository.findByJobId(jobId);
            //throw error if job is null
            if (job == null) {
                throw new IllegalArgumentException("Job not found for the given jobId");
            }
            //check if ph member of the job to be updated is the same as the user trying to update
            if (!rollNo.equals(job.getUserData().getRollNo())) {
                throw new SecurityException("User is not authorized to fetch the job details");
            }
                return applicationRepository.findEligibleStudentsForNextRound(jobId);
        } catch (IllegalArgumentException | SecurityException e) {
            // Handle specific exceptions
            System.err.println(e.getMessage());
            throw e; // Rethrow the exception if needed or handle accordingly
        } catch (Exception e) {
            // Handle generic exceptions
            e.printStackTrace();
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }

    @Override
    @Transactional
    public boolean updateRoundsClearedAndSelection(List<String> rollNos, String jobId, String rollNo) {
        try {
            Job job = jobRepository.findById(jobId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid job ID provided"));

            //check if ph member trying to update the job details has the roll number rollNo
            if (!rollNo.equals(job.getUserData().getRollNo())) {
                throw new SecurityException("User is not authorized to fetch the job details");
            }

            int numberOfRounds = job.getNumberOfRounds();

            for (String rollNo1 : rollNos) {
                ApplicationId applicationId = new ApplicationId(rollNo1, jobId);
                Application application = applicationRepository.findApplicationById(applicationId);

                if (application == null) {
                    throw new IllegalArgumentException(
                            "Application not found for roll number " + rollNo1 + " and job ID " + jobId);
                }

                if (application.getRoundsCleared() < numberOfRounds) {
                    application.setRoundsCleared(application.getRoundsCleared() + 1);
                }

                if (application.getRoundsCleared() == numberOfRounds) {
                    application.setSelected(true);
                }

                applicationRepository.save(application);
            }

            if (job.getCompletedRounds() < numberOfRounds) {
                job.setCompletedRounds(job.getCompletedRounds() + 1);
            }
            jobRepository.save(job);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Application getApplicationById(String rollNo, String jobId) {
        ApplicationId applicationId = new ApplicationId(rollNo, jobId);
        Application application = null;

        try {
            application = applicationRepository.findApplicationById(applicationId);
            if (application == null) {
                throw new IllegalArgumentException("Application not found for roll number " + rollNo + " and job ID " + jobId);
            }
        } catch (IllegalArgumentException e) {
            // Handle specific cases where the application is not found
            System.out.println("Error: " + e.getMessage());
            // You can log the error or perform any other necessary action here
        } catch (Exception e) {
            // Handle any other unforeseen exceptions
            System.out.println("An unexpected error occurred: " + e.getMessage());
            // Additional logging or actions can be performed here
        }

        return application;
    }



}
