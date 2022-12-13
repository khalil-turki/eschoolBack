package edu.esprit.kaddem.model.notification;

import edu.esprit.kaddem.lib.AbstractEntity;
import edu.esprit.kaddem.model.user.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue
    private Integer id;

    private String content;
    private String title;
    private String icon;
    
    @OneToMany(mappedBy = "notification")
    private List<NotificationUser> users = new ArrayList<>();

    public Notification(String content, String title, String icon, String actionUrl) {
        this.content = content;
        this.title = title;
        this.icon = icon;
        this.actionUrl = actionUrl;
    }

    private String actionUrl;
}
