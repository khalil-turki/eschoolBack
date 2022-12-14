package edu.esprit.kaddem.controller;

import edu.esprit.kaddem.dto.NotificationDto;
import edu.esprit.kaddem.model.notification.Notification;
import edu.esprit.kaddem.model.user.Utilisateur;
import edu.esprit.kaddem.services.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notifications")
public class NotificationsController {
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private NotificationService notificationService;



    @GetMapping("/me")
    public List<NotificationDto> getNotifications() {
        var authed = SecurityContextHolder.getContext().getAuthentication();
        if(authed.getPrincipal() instanceof Utilisateur user) {
            return notificationService.getNotifications(user).stream()
                    .map(notification -> modelMapper.map(notification, NotificationDto.class))
                    .collect(Collectors.toList());
        } else return List.of();
    }

    @GetMapping("/test")
    public void testNotification() {
        Utilisateur user = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        notificationService.sendNotification(user, new Notification("And this is its body", "You have a new notification!", "alert-octagon", "/dashboard/ecommerce"));
    }

    @PostMapping("/markAsRead/{id}")
    public void markAsRead(@PathVariable Integer id) {
        Utilisateur user = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        notificationService.markAsRead(id, user);
    }

    @PostMapping("/markAllAsRead")
    public void markAllAsRead() {
        Utilisateur user = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        notificationService.markAllAsRead(user);
    }

}