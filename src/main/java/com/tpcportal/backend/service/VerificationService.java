package com.tpcportal.backend.service;

import com.tpcportal.backend.model.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VerificationService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${verification.code.expiry.minutes}")
    private int expiryMinutes;

    private final Map<String, VerificationCode> verificationCodes = new ConcurrentHashMap<>();

    public boolean sendVerificationCode(String email) {
        String code = generateVerificationCode();
//        System.out.println("Debug 5.1");
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject("Your Verification Code");
            mailMessage.setText("Your verification code is: " + code);
//            System.out.println("Debug 5.2");
//            System.out.println(mailMessage);
            javaMailSender.send(mailMessage);
            System.out.println("Debug 5.3");

            verificationCodes.put(email, new VerificationCode(code, expiryMinutes));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean verifyCode(String email, String code) {
        VerificationCode verificationCode = verificationCodes.get(email);
        if (verificationCode == null || verificationCode.isExpired()) {
            verificationCodes.remove(email);
            return false;
        }
        return verificationCode.getCode().equals(code);
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
