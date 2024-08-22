package com.tpcportal.backend.service;

import com.tpcportal.backend.dto.ClientUserDTO;
import com.tpcportal.backend.entity.Role;
import com.tpcportal.backend.entity.UserCredentials;
import com.tpcportal.backend.entity.UserData;
import com.tpcportal.backend.repository.RoleRepository;
import com.tpcportal.backend.repository.UserDataRepository;
import com.tpcportal.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

@Autowired
private UserRepository userRepository;

//    @Autowired
//    private RoleRepository roleRepository;

//    @Autowired
//    private UserDataRepository userDataRepository;



    private List<GrantedAuthority>authorities;

    @Override
    public UserDetails loadUserByUsername(String rollno) throws UsernameNotFoundException {
        UserCredentials user = userRepository.findByRollno(rollno);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with roll number: " + rollno);
        }
        System.out.println("Debug h1");
        List<Role> roles = user.getRoles();
        System.out.println("Debug h2");
        //for debugging purpose
        System.out.println(roles);
        authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                .collect(Collectors.toList());
        System.out.println(authorities);
        return new User(user.getRollno(), user.getPassword(), authorities);
    }

    public ClientUserDTO fetchDetails(String rollno, String token){
        UserCredentials userCredentials = userRepository.findByRollno(rollno);
        UserData userData = userCredentials.getUserData();
        //for debugging purpose
        System.out.println(userData);
        ClientUserDTO clientUserDTO = new ClientUserDTO(rollno,userData.getMiddleName(),userData.getFirstName(),userData.getLastName(),userData.getDob(), userCredentials.getInstiMail(),userCredentials.getPersonalMail(),userData.getDegree(),userData.getBranch(),userData.getCpi(),userData.getResume(),userData.getLinkedin(),userData.getPhoneNumber(),false,false,false,token,userData.getGender(),userData.getTenthPercentage(),userData.getTwelfthOrDiplomaPercentage());
        for(GrantedAuthority authority : authorities){
            String role=authority.getAuthority();
            if(role.equals("ROLE_STUDENT")) clientUserDTO.setStudent(true);
            if(role.equals("ROLE_PH_MEMBER")) clientUserDTO.setPh_member(true);
            if(role.equals("ROLE_ADMIN")) clientUserDTO.setAdmin(true);
        }
        return clientUserDTO;
    }
}
