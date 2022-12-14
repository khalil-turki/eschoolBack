package edu.esprit.kaddem.services;

import edu.esprit.kaddem.model.notification.Notification;
import edu.esprit.kaddem.model.notification.NotificationUser;
import edu.esprit.kaddem.model.notification.NotificationUserPK;
import edu.esprit.kaddem.model.user.Utilisateur;
import edu.esprit.kaddem.repository.NotificationRepository;
import edu.esprit.kaddem.repository.NotificationUserRepository;
import edu.esprit.kaddem.utils.WebsocketPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    @Autowired
    private WebsocketPublisher websocketPublisher;
    @Autowired
    private NotificationUserRepository notificationUserRepository;

    @Autowired private NotificationRepository notificationRepository;

    public void sendNotification(Utilisateur user, Notification notification) {
        notificationRepository.save(notification);
        var notificationUser = new NotificationUser();
        notificationUser.setUser(user);
        notificationUser.setNotification(notification);
        notificationUser.setDesignation(new NotificationUserPK(user.getId(), notification.getId()));
        notificationUserRepository.save(notificationUser);
        websocketPublisher.publish(notification, user.getUsername());
    }

    public List<Notification> getNotifications(Utilisateur user) {
        return notificationUserRepository.findAllByUser(user).stream()
                .map(NotificationUser::getNotification)
                .collect(Collectors.toList());
    }

    public void markAsRead(Integer id, Utilisateur user) {
        var notificationUser = notificationUserRepository.findNotificationByIdAndUser(id, user.getId());
        notificationUser.ifPresent(notificationUser1 -> {
            notificationUser1.setRead(true);
            notificationUserRepository.save(notificationUser1);
        });
    }

    public void markAllAsRead(Utilisateur user) {
        var notifications = notificationUserRepository.findAllByUser(user);
        notifications.forEach(notificationUser -> {
            notificationUser.setRead(true);
            notificationUserRepository.save(notificationUser);
        });
    }
}
