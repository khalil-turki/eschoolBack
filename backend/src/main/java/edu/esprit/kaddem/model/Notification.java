package edu.esprit.kaddem.model;

import edu.esprit.kaddem.lib.AbstractEntity;
import edu.esprit.kaddem.model.user.Utilisateur;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Notification {

    @Id
    @GeneratedValue
    private Integer id;

    private String content;
    private String title;
    private String icon;
    
    private Boolean isRead = false;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Utilisateur> destinations = new ArrayList<>();

    public Notification(String content, String title, String icon) {
        this.content = content;
        this.title = title;
        this.icon = icon;
    }

    public Notification() {
    }


}
