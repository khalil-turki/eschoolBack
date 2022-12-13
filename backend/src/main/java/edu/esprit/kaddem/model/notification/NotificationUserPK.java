package edu.esprit.kaddem.model.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class NotificationUserPK implements Serializable {
    private static final long serialVersionUID = 4090084860240079428L;
    private Integer userId;
    private Integer notificationId;

    public NotificationUserPK(Integer userId, Integer notificationId) {
        this.userId = userId;
        this.notificationId = notificationId;
    }
}
