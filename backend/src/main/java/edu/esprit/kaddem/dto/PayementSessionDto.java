package edu.esprit.kaddem.dto;


import edu.esprit.kaddem.lib.AbstractDto;
import edu.esprit.kaddem.model.PaymentSession;
import edu.esprit.kaddem.model.user.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * A DTO for the {@link PaymentSession} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PayementSessionDto extends AbstractDto<PaymentSession> implements Serializable {
    private Utilisateur initiator;
    private boolean paid;
    private String stripe_checkout_session_id;

}
