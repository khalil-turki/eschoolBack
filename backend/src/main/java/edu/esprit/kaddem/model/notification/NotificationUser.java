package edu.esprit.kaddem.model.notification;

import edu.esprit.kaddem.model.user.Utilisateur;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class NotificationUser {
    @EmbeddedId private NotificationUserPK designation;
    @ManyToOne
    @MapsId("userId")
    private Utilisateur user;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("notificationId")
    private Notification notification;

    @Column
    private boolean isRead;
}
