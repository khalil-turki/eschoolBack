package edu.esprit.kaddem.dto;

import lombok.Data;

@Data
public class NotificationDto {
    private Integer id;
    private String content;
    private String title;
    private String icon;
    private Boolean isRead;
    private String actionUrl;
}
