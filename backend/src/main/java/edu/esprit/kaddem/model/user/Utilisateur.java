package edu.esprit.kaddem.model.user;

import edu.esprit.kaddem.lib.AbstractEntity;
import edu.esprit.kaddem.listeners.UtilisateurListener;
import edu.esprit.kaddem.model.Adresse;
import edu.esprit.kaddem.model.Ecole;
import edu.esprit.kaddem.model.PaymentSession;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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

    @Column(name = "email")
    private String email;

    @Column(name = "numtel")
    private String numTel;

    @Column(name = "datedenaissance")
    private Date dateDeNaissance;

    @Column(name = "password")
    private String password;

    @Transient
    private String plainPassword;

    @OneToOne
    private Adresse adresse;

    @Column(name = "photo")
    private String photo;

    @Column(name = "is_using_2fa")
    private Boolean isUsing2FA;

    @Column(name = "secret_2fa")
    private String secret;

    @Column(name = "avatar")
    private String avatar;

    @Column
    private String confirmationToken;

    @ManyToOne
    @JoinColumn(name = "id_ecole")
    private Ecole ecole;

    @OneToMany(mappedBy = "initiator")
    private List<PaymentSession> paymentSessions;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, insertable = false, updatable = false)
    private Role role;


    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, insertable = false, updatable = false)
    private Gender gender = Gender.UNSPECIFIED;

    @Column(name = "enabled")
    private boolean enabled = true;

    @Override
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
        return this.enabled;
    }
}
