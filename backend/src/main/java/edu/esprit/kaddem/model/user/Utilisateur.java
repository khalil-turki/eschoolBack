package edu.esprit.kaddem.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.esprit.kaddem.deserializers.CustomAuthorityDeserializer;
import edu.esprit.kaddem.lib.AbstractEntity;
import edu.esprit.kaddem.listeners.UtilisateurListener;
import edu.esprit.kaddem.model.Adresse;
import edu.esprit.kaddem.model.Ecole;
import edu.esprit.kaddem.model.notification.Notification;
import edu.esprit.kaddem.model.PaymentSession;
import edu.esprit.kaddem.model.notification.NotificationUser;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@Component
@EntityListeners(UtilisateurListener.class)
public class Utilisateur extends AbstractEntity<Utilisateur> implements UserDetails {
    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "numtel")
    private String numTel;

    @Column(name = "datedenaissance")
    private Date dateDeNaissance;

    @Column(name = "password")
    private String password;

    @Transient
    private String plainPassword;


    @Column(name = "photo")
    private String photo;

    @Column(name = "is_using_2fa", columnDefinition = "boolean default false")
    private Boolean isUsing2FA = false;

    @Column(name = "secret_2fa")
    private String secret;

    @Column(name = "avatar")
    private String avatar;

    @Column
    private String confirmationToken;

    @ManyToOne
    @JoinColumn(name = "id_ecole")
    private Ecole ecole;

    @OneToMany(mappedBy = "initiator", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<PaymentSession> paymentSessions = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, insertable = false, updatable = false)
    private Role role;

    @Column(name = "enabled", columnDefinition = "boolean default true")
    private Boolean enabled = true;

    @Override
    @JsonDeserialize(using = CustomAuthorityDeserializer.class)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.getRole().toString()));
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(this.enabled == null) {
            this.enabled = true;
        }
        return this.enabled;
    }

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<NotificationUser> users = new ArrayList<>();
}
