package edu.esprit.kaddem.model.user;

import edu.esprit.kaddem.lib.AbstractEntity;
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
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@Component
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
    private Instant dateDeNaissance;

    @Column(name = "password")
    private String password;

    @OneToOne
    private Adresse adresse;

    @Column(name = "photo")
    private String photo;

    @Column(name = "avatar")
    private String avatar;

    @Column
    private String confirmationToken;

    @ManyToOne
    @JoinColumn(name = "idecole")
    private Ecole ecole;

    @OneToMany(mappedBy = "initiator")
    private List<PaymentSession> paymentSessions;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, insertable = false, updatable = false)
    private Role role;

    private String plainPassword;

    @Column(name = "enabled")
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + this.getRole().toString()));
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
