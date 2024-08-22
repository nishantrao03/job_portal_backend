package com.tpcportal.backend.service;

import com.tpcportal.backend.dto.UserDTO;
import com.tpcportal.backend.embeddedids.RoleId;
import com.tpcportal.backend.entity.Alumni;
import com.tpcportal.backend.entity.Role;
import com.tpcportal.backend.entity.UserCredentials;
import com.tpcportal.backend.entity.UserData;
import com.tpcportal.backend.repository.AlumniRepository;
import com.tpcportal.backend.repository.RoleRepository;
import com.tpcportal.backend.repository.UserDataRepository;
import com.tpcportal.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDataRepository userDetailsRepository;

//    @Autowired
//    private RoleRepository roleRepository;

    @Autowired
    private AlumniRepository alumniRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public boolean addStudent(UserDTO requestDTO) {
        try {
            // 1. Save to passwords table
            System.out.println("Debug 7.3");
            UserCredentials userCredentials = new UserCredentials();
            userCredentials.setRollno(requestDTO.getRollNo());
            userCredentials.setInstiMail(requestDTO.getInstiMail());
            userCredentials.setPersonalMail(requestDTO.getPersonalMail());
            userCredentials.setPassword(passwordEncoder.encode(requestDTO.getPassword())); // Ensure you have passwordEncoder bean
            System.out.println(requestDTO);
            System.out.println("Debug 7.2");
            System.out.println(userCredentials);
            //userRepository.save(userCredentials);
            System.out.println("Debug 7.4");
            // 2. Save to user_details table
            UserData userDetails = new UserData();
            userDetails.setRollNo(requestDTO.getRollNo());
            userDetails.setFirstName(requestDTO.getFirstName());
            userDetails.setMiddleName(requestDTO.getMiddleName());
            userDetails.setLastName(requestDTO.getLastName());
            userDetails.setDob(requestDTO.getDob());
            userDetails.setDegree(requestDTO.getDegree());
            userDetails.setBranch(requestDTO.getBranch());
            userDetails.setCpi(requestDTO.getCpi());
            userDetails.setResume(requestDTO.getResume());
            userDetails.setLinkedin(requestDTO.getLinkedin());
            userDetails.setPhoneNumber(requestDTO.getPhoneNumber());
            userDetails.setGender(requestDTO.getGender());
            userDetails.setTenthPercentage(requestDTO.getTenthPercentage());
            userDetails.setTwelfthOrDiplomaPercentage(requestDTO.getTwelfthOrDiplomaPercentage());
            userCredentials.setUserData(userDetails);
            System.out.println("Debug 7.5");
            //userDetailsRepository.save(userDetails);
            System.out.println("Debug 7.6");
            // 3. Add role "STUDENT"
            Role studentRole = new Role(requestDTO.getRollNo(), "STUDENT");
            System.out.println("Debug 7.7");
            userCredentials.addRole(studentRole);
            userRepository.save(userCredentials);
            //roleRepository.save(role);
            System.out.println("Debug 7.8");
            return true;
        } catch (Exception e) {
            System.out.println("Debug catch 1");
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public boolean addForVerification(UserDTO userDTO){
        try {
            Alumni alumni = new Alumni(userDTO.getRollNo(),userDTO.getFirstName(),userDTO.getMiddleName(),userDTO.getLastName(),userDTO.getDegree(),userDTO.getBranch(),userDTO.getCpi(),userDTO.getResume(),userDTO.getLinkedin(),userDTO.getDob(),userDTO.getPhoneNumber(),bCryptPasswordEncoder.encode(userDTO.getPassword()),userDTO.getPersonalMail());
            alumniRepository.save(alumni);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public boolean addRole(String rollNo, String role) {
        //While hosting, mind that whenever you add a new role to a roll no, make sure that whenever the user with that roll number accesses the application the next time, whether or not the token has expired or not, the user will have to login again. In one way, what you can do is that you can make the token null or expired for that user.
        try {
            System.out.println("Debug 121");

            // Fetch the user by roll number
            UserCredentials userCredentials = userRepository.findByRollno(rollNo);
            if (userCredentials == null) {
                System.out.println("User with roll number " + rollNo + " not found.");
                return false;
            }

            // Fetch current roles of the user
            List<Role> currentRoles = userCredentials.getRoles();
            boolean isPhMember = currentRoles.stream().anyMatch(r -> r.getRole().equals("PH_MEMBER"));
            boolean isAdmin = currentRoles.stream().anyMatch(r -> r.getRole().equals("ADMIN"));

            // Add the role "PH_MEMBER" if the user is not a ph_member
            if (!isPhMember) {
                Role phMemberRole = new Role(rollNo, "PH_MEMBER");
                userCredentials.addRole(phMemberRole);
            }

            // Add the role "ADMIN" if the user is not an admin and the role to be added is "ADMIN"
            if (!isAdmin && role.equals("ADMIN")) {
                Role adminRole = new Role(rollNo, "ADMIN");
                userCredentials.addRole(adminRole);
            }

            // Persist the changes
            userRepository.save(userCredentials);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public boolean editProfile(UserDTO userDTO){
        try{
            //fetch user credentials from userDTO.rollNo()
            UserCredentials userCredentials = userRepository.findByRollno(userDTO.getRollNo());

            if (userCredentials == null) {
                throw new UsernameNotFoundException("User not found with roll number: " + userDTO.getRollNo());
            }
//            //fetch the roles from the user credentials
//            List<Role> roles = tempUserCredentials.getRoles();
//            //fetch password from user credentials
//            String password = tempUserCredentials.getPassword();
            //make the new USerCredentials ans UserData object from userDTO
            //UserCredentials userCredentials = new UserCredentials();
            //userCredentials.setRollno(userDTO.getRollNo());
            //userCredentials.setInstiMail(userDTO.getInstiMail());
            userCredentials.setPersonalMail(userDTO.getPersonalMail());
//            userCredentials.setPassword(password);
//            userCredentials.setRoles(roles);
            UserData userDetails = userCredentials.getUserData();
            //userDetails.setRollNo(userDTO.getRollNo());
            userDetails.setFirstName(userDTO.getFirstName());
            userDetails.setMiddleName(userDTO.getMiddleName());
            userDetails.setLastName(userDTO.getLastName());
            userDetails.setDob(userDTO.getDob());
            userDetails.setDegree(userDTO.getDegree());
            userDetails.setBranch(userDTO.getBranch());
            userDetails.setCpi(userDTO.getCpi());
            userDetails.setResume(userDTO.getResume());
            userDetails.setLinkedin(userDTO.getLinkedin());
            userDetails.setPhoneNumber(userDTO.getPhoneNumber());
            userDetails.setGender(userDTO.getGender());
            userDetails.setTenthPercentage(userDTO.getTenthPercentage());
            userDetails.setTwelfthOrDiplomaPercentage(userDTO.getTwelfthOrDiplomaPercentage());
            //set UserData in UserCredentials
            userCredentials.setUserData(userDetails);
            //finally save the UserCredentials object
            userRepository.save(userCredentials);
            return true;
        } catch (Exception e) {
            System.out.println("Debug catch 1");
            e.printStackTrace();
            return false;
        }
    }

    public boolean changePassword(String rollNo, String oldPassword, String newPassword) {
        UserCredentials userCredentials = userRepository.findByRollno(rollNo);
        if (userCredentials == null) {
            throw new UsernameNotFoundException("User not found");
        }


        if (!passwordEncoder.matches(oldPassword, userCredentials.getPassword())) {
            throw new IllegalArgumentException("Invalid old password");
        }

        userCredentials.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(userCredentials);
        return true;
    }
}
