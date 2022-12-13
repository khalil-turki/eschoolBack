package edu.esprit.kaddem.utils;


import com.nimbusds.oauth2.sdk.token.AccessToken;
import edu.esprit.kaddem.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

@Component
public class WebsocketAuthInterceptor implements ChannelInterceptor {

    @Autowired
    private JwtTokenUtil jwtUtil;
    @Autowired
    private WebsocketUserSessionStore sessionStore;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        String sessionId = accessor.getMessageHeaders().get("simpSessionId").toString();

        if (StompCommand.CONNECT == accessor.getCommand()) {
            String jwtToken = accessor.getFirstNativeHeader("Authorization").substring(7);
            var username = jwtUtil.getUsernameFromToken(jwtToken);
            sessionStore.add(sessionId, username);
        } else if (StompCommand.DISCONNECT == accessor.getCommand()) {
            sessionStore.remove(sessionId);
        }
        return message;
    }
}