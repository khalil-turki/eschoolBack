package edu.esprit.kaddem.services;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public static void SendEmail(String email, String subject, String body) {
        System.out.println("Sending email to " + email);
    }
}
