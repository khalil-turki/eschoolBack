package edu.esprit.kaddem.services;

import edu.esprit.kaddem.model.Notification;
import edu.esprit.kaddem.model.user.Utilisateur;
import edu.esprit.kaddem.repository.NotificationRepository;
import edu.esprit.kaddem.repository.UtilisateurRepository;
import edu.esprit.kaddem.utils.WebsocketPublisher;
import jdk.jfr.EventType;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Service
public class NotificationService {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;
    @Autowired
    private WebsocketPublisher websocketPublisher;


    public void sendNotification(Utilisateur user, Notification notification) {
        notification.getDestinations().add(user);
        entityManager.persist(notification);
        websocketPublisher.publish(notification, user.getUsername());
    }
}
