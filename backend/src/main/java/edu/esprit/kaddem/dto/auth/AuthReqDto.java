package edu.esprit.kaddem.dto.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthReqDto implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;
}