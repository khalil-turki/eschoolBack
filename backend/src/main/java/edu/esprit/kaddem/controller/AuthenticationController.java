package edu.esprit.kaddem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.esprit.kaddem.annotations.RateLimit;
import edu.esprit.kaddem.annotations.TrackPerformance;
import edu.esprit.kaddem.dto.AbstractUserDto;
import edu.esprit.kaddem.dto.EtudiantDto;
import edu.esprit.kaddem.dto.ParentDto;
import edu.esprit.kaddem.dto.ProfesseurDto;
import edu.esprit.kaddem.dto.auth.AuthReqDto;
import edu.esprit.kaddem.dto.auth.AuthResDto;
import edu.esprit.kaddem.dto.auth.ReqNewPasswdDto;
import edu.esprit.kaddem.dto.auth.ResPasswdDto;
import edu.esprit.kaddem.model.user.Etudiant;
import edu.esprit.kaddem.model.user.Utilisateur;
import edu.esprit.kaddem.services.AuthenticationService;
import edu.esprit.kaddem.services.EmailService;
import io.swagger.annotations.Api;
import org.jboss.aerogear.security.otp.Totp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
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


    @Autowired
    EmailService emailService;

    @RateLimit(3)
    @TrackPerformance
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthReqDto authReqDto) {
        var token = authenticationService.getToken(authReqDto.getUsername(), authReqDto.getPassword(), authReqDto.getRememberMe());

        var user = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.getIsUsing2FA()) {
            if(authReqDto.getTotp() == null || authReqDto.getTotp().isEmpty())
                throw new AccessDeniedException("2FA is enabled for this account");
            var totp = new Totp(user.getSecret());
            if(!totp.verify(authReqDto.getTotp()))
                throw new AccessDeniedException("TOTP is invalid");
        }
        var retVal = oMapper.convertValue(getAuthenticatedUser(), Map.class);
        retVal.put("token", token);
        return ResponseEntity.ok(retVal);
    }

    @GetMapping(value = "/refresh")
    @TrackPerformance
    public AuthResDto refreshtoken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        var newToken = authenticationService.refreshToken(token);
        return new AuthResDto(newToken);
    }

    @GetMapping("/me")
    public AbstractUserDto<? extends Utilisateur> getAuthenticatedUser() {
        Utilisateur user = null;
        try {
            user = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new AccessDeniedException("Access denied");
        }
        // switch to check for roles
        return switch (user.getRole()) {
            case ROLE_ETUDIANT -> mapper.map(user, EtudiantDto.class);
            case ROLE_ADMIN, ROLE_PROFESSEUR -> mapper.map(user, ProfesseurDto.class);
            case ROLE_PARENT -> mapper.map(user, ParentDto.class);
            default -> throw new RuntimeException("Unknown role");
        };
    }

    @PostMapping("/signup")
    @TrackPerformance
    public ResponseEntity<?> signup(@RequestBody EtudiantDto etudiantDto) {
        var user = mapper.map(etudiantDto, Etudiant.class);
        return ResponseEntity.ok(mapper.map(authenticationService.signup(user), EtudiantDto.class));
    }

    @PostMapping("/request-password-reset")
    public ResponseEntity<?> resetPassword(@RequestBody ReqNewPasswdDto reqDto) {
        authenticationService.forgotPassword(reqDto.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password/{token}")
    public ResponseEntity<?> resetPassword(@PathVariable String token, @RequestBody ResPasswdDto resPwd) {
        authenticationService.resetPassword(token, resPwd.getEmail(), resPwd.getPassword());
        return ResponseEntity.ok().build();
    }
}