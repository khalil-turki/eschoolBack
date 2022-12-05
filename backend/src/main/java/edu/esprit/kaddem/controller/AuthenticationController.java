package edu.esprit.kaddem.controller;

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
import java.util.Map;

@Api
@RestController()
@CrossOrigin
@RequestMapping("/auth")
public class AuthenticationController {
    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthReqDto authReqDto) {
        return ResponseEntity.ok(new AuthResDto(authenticationService.getToken(authReqDto.getUsername(), authReqDto.getPassword())));
    }

    @GetMapping(value = "/refresh")
    public AuthResDto refreshtoken(HttpServletRequest request) {
        // From the HttpRequest get the claims
        DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");
        Map<String, Object> expectedMap = JwtTokenUtil.getMapFromIoJsonwebtokenClaims(claims);
        String token = authenticationService.refreshToken(expectedMap, expectedMap.get("sub").toString());
        return new AuthResDto(token);
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