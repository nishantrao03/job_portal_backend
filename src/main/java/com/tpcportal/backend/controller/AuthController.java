package com.tpcportal.backend.controller;

import com.tpcportal.backend.dto.ClientUserDTO;
import com.tpcportal.backend.dto.UserDTO;
import com.tpcportal.backend.entity.UserData;
import com.tpcportal.backend.helper.JwtUtil;
import com.tpcportal.backend.model.JwtRequest;
import com.tpcportal.backend.model.JwtResponse;
import com.tpcportal.backend.repository.UserDataRepository;
import com.tpcportal.backend.service.CustomUserDetailsService;
import com.tpcportal.backend.service.RegistrationService;
import com.tpcportal.backend.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private UserDataRepository userDataRepository;

    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) {
        //System.out.println(jwtRequest.getRollno()+" "+jwtRequest.getPassword());

        try {
            //System.out.println("Attempting authentication for rollno: " + jwtRequest.getRollno());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getRollno(), jwtRequest.getPassword())
            );
            //System.out.println("Authentication successful for rollno: " + jwtRequest.getRollno());
        } catch (BadCredentialsException e) {
            //System.out.println("Authentication failed for rollno: " + jwtRequest.getRollno());
            return ResponseEntity.status(401).body("Incorrect roll number or password");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An unexpected error occurred");
        }


        try {
            System.out.println("Debug i1");
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getRollno());
            System.out.println(userDetails);
            //System.out.println(userDetails.getUsername()+" "+userDetails.getPassword());
            if (userDetails == null) {
                throw new UsernameNotFoundException("User not found with roll number: " + jwtRequest.getRollno());
            }
            System.out.println(userDetails.getAuthorities());

            //System.out.println("Debug 2");
            String token = jwtUtil.generateToken(jwtRequest.getRollno());
            ClientUserDTO clientUserDTO = customUserDetailsService.fetchDetails(jwtRequest.getRollno(),token);
            System.out.println(token);
            return ResponseEntity.ok(clientUserDTO);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestBody String mail) {
        System.out.println(mail);
        boolean isCodeSent = verificationService.sendVerificationCode(mail);

        if (isCodeSent) {
            return ResponseEntity.ok("Verification code sent successfully.");
        } else {
            return ResponseEntity.status(500).body("Failed to send verification code.");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDTO userDTO) {
        String instiMail = userDTO.getInstiMail();
        String verificationCode = userDTO.getCode();
        System.out.println("Debug 6.1");
        System.out.println(verificationCode);
        System.out.println(userDTO.toString());
        System.out.println(userDTO);
        // Verify the code
        if (!verificationService.verifyCode(instiMail, verificationCode)) {
            return ResponseEntity.status(401).body("Invalid or expired verification code.");
        }
        System.out.println(userDTO.getRollNo()+" "+userDTO.getInstiMail());
        System.out.println(userDTO);
        System.out.println("Debug 6.2");
        // Code is verified, proceed to add student details to database
        registrationService.addStudent(userDTO);

        System.out.println("Debug 6.3");
        // Authenticate and generate JWT token
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getRollNo(), userDTO.getPassword())
            );
            UserDetails authenticatedUser = customUserDetailsService.loadUserByUsername(userDTO.getRollNo());
            String token = jwtUtil.generateToken(authenticatedUser.getUsername());
            System.out.println(token);
            ClientUserDTO clientUserDTO = new ClientUserDTO(userDTO.getRollNo(), userDTO.getMiddleName(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getDob(), userDTO.getInstiMail(), userDTO.getPersonalMail(), userDTO.getDegree(), userDTO.getBranch(), userDTO.getCpi(),userDTO.getResume(),userDTO.getLinkedin(),userDTO.getPhoneNumber(),true,false,false,token,userDTO.getGender(),userDTO.getTenthPercentage(),userDTO.getTwelfthOrDiplomaPercentage());
            return ResponseEntity.ok(clientUserDTO);
        }catch (Exception e) {
            return ResponseEntity.status(401).body("Authentication failed: " + e.getMessage());
        }
    }

    @PostMapping("/signup-alumni")
    public ResponseEntity<?> signup_alumni(@RequestBody UserDTO userDTO) {
        String mail = userDTO.getPersonalMail();
        String verificationCode = userDTO.getCode();
        System.out.println("Debug 6.1");
        // Verify the code
        if (!verificationService.verifyCode(mail, verificationCode)) {
            return ResponseEntity.status(401).body("Invalid or expired verification code.");
        }
        System.out.println("Debug 6.2");
        boolean isAdded = registrationService.addForVerification(userDTO);
        //do the db changes for gender, tenth percentage and twelfth percentage
        if(isAdded){
            return ResponseEntity.status(200).body("Alumni registration request submitted successfully.");
        }
        return ResponseEntity.status(500).body("Failed to submit alumni registration request.");

    }

    @PostMapping("/validate-token")
    public ResponseEntity<String> validateToken(@RequestBody String token) {
        try {
            // Check if token is expired

            System.out.println(token);
            boolean isExpired = jwtUtil.isTokenExpired(token);
            System.out.println("Debug");
            if (isExpired) {
                return ResponseEntity.badRequest().body("Token expired");
            } else {
                System.out.println("Hello");
                return ResponseEntity.ok("Token is valid");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid token");
        }
    }

    @GetMapping("/welcome")
    public String welcome(){
        System.out.println("Debug 4");
        return "hello";
    }
}
