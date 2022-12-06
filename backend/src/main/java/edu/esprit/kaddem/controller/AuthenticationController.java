package edu.esprit.kaddem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.esprit.kaddem.dto.AbstractUserDto;
import edu.esprit.kaddem.dto.EtudiantDto;
import edu.esprit.kaddem.dto.ParentDto;
import edu.esprit.kaddem.dto.ProfesseurDto;
import edu.esprit.kaddem.dto.auth.AuthReqDto;
import edu.esprit.kaddem.dto.auth.AuthResDto;
import edu.esprit.kaddem.model.user.Etudiant;
import edu.esprit.kaddem.model.user.Utilisateur;
import edu.esprit.kaddem.services.AuthenticationService;
import edu.esprit.kaddem.utils.JwtTokenUtil;
import io.jsonwebtoken.impl.DefaultClaims;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api
@RestController()
@CrossOrigin
@RequestMapping("/auth")
public class AuthenticationController {
    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    private AuthenticationService authenticationService;

    ObjectMapper oMapper = new ObjectMapper();


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthReqDto authReqDto) {
        var token = authenticationService.getToken(authReqDto.getUsername(), authReqDto.getPassword());
        var user = getAuthenticatedUser();
        var retVal = oMapper.convertValue(user, Map.class);
        retVal.put("token", token);
        return ResponseEntity.ok(retVal);
    }

    @GetMapping(value = "/refresh")
    public AuthResDto refreshtoken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        var newToken = authenticationService.refreshToken(token);
        return new AuthResDto(newToken);
    }

    @GetMapping("/me")
    public AbstractUserDto<? extends Utilisateur> getAuthenticatedUser() {
        var user = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // switch to check for roles
        switch (user.getRole()) {
            case ROLE_ETUDIANT:
                return mapper.map(user, EtudiantDto.class);
            case ROLE_ADMIN:
            case ROLE_PROFESSEUR:
                return mapper.map(user, ProfesseurDto.class);

            case ROLE_PARENT:
                return mapper.map(user, ParentDto.class);
            default:
                throw new RuntimeException("Unknown role");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody EtudiantDto etudiantDto) {
        var user = mapper.map(etudiantDto, Etudiant.class);
        return ResponseEntity.ok(mapper.map(authenticationService.signup(user), EtudiantDto.class));
    }
}