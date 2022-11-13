package edu.esprit.kaddem.model;

import edu.esprit.kaddem.lib.AbstractEntity;
import edu.esprit.kaddem.model.user.Utilisateur;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
public class PaymentSession extends AbstractEntity<PaymentSession> {
    @ManyToOne
    private Utilisateur initiator;

    @Column
    private boolean paid;

    @Column
    private String stripe_checkout_session_id;
}
