package edu.esprit.kaddem.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AuthResDto {
    private String token;

    public AuthResDto(String token) {
        this.token = token;
    }
}
