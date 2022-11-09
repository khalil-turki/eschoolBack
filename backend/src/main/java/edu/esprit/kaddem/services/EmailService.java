package edu.esprit.kaddem.services;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public static void SendEmail(String email, String subject, String body) {
        throw new NotYetImplementedException();
    }
}
