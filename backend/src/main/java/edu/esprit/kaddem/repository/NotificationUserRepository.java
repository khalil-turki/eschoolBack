package edu.esprit.kaddem.repository;

import edu.esprit.kaddem.model.notification.NotificationUserPK;
import edu.esprit.kaddem.model.notification.NotificationUser;
import edu.esprit.kaddem.model.user.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationUserRepository extends JpaRepository<NotificationUser, NotificationUserPK>, JpaSpecificationExecutor<NotificationUser> {
    List<NotificationUser> findAllByUser(Utilisateur user);

    @Query("select n from NotificationUser n where n.id.userId = ?1 and n.id.notificationId = ?2")
    Optional<NotificationUser> findNotificationByIdAndUser(Integer userId, Integer notificationId);
}