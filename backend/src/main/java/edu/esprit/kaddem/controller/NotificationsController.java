package edu.esprit.kaddem.controller;

import edu.esprit.kaddem.dto.NotificationDto;
import edu.esprit.kaddem.model.Notification;
import edu.esprit.kaddem.model.user.Utilisateur;
import edu.esprit.kaddem.services.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Utilisateur user = (Utilisateur) SecurityContextHolder.getContext().getAuthentication();
        return user.getNotifications().stream()
                .map(notification -> modelMapper.map(notification, NotificationDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/test")
    public void testNotification() {
        Utilisateur user = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        notificationService.sendNotification(user, new Notification("test", "test", "test"));
    }
}