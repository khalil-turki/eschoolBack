package edu.esprit.kaddem.dto;

import lombok.Data;

@Data
public class NotificationDto {
    private String content;
    private String title;
    private String icon;
    private Boolean isRead;
}
