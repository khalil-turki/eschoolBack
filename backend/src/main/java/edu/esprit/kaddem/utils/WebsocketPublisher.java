package edu.esprit.kaddem.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.esprit.kaddem.dto.NotificationDto;
import edu.esprit.kaddem.model.Notification;
import jdk.jfr.EventType;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WebsocketPublisher {
    @Autowired
    SimpMessagingTemplate messagingTemplate;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    WebsocketUserSessionStore sessionStore;

    @SneakyThrows
    public void publish(Object eventData, String username) {
        var sessionId = sessionStore.getSessionId(username);

        assert eventData instanceof Notification;
        messagingTemplate.convertAndSendToUser(
                sessionId,
                "/queue/notification",
                objectMapper.writeValueAsString(modelMapper.map(eventData, NotificationDto.class)),
                toMessageHeaders(sessionId)
        );
    }

    private MessageHeaders toMessageHeaders(String it) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(it);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
}