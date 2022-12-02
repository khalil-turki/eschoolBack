package edu.esprit.kaddem.controller.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import edu.esprit.kaddem.dto.EtudiantDto;
import edu.esprit.kaddem.dto.auth.AuthReqDto;
import edu.esprit.kaddem.dto.auth.AuthResDto;
import edu.esprit.kaddem.exception.EntityNotFoundException;
import edu.esprit.kaddem.model.user.Etudiant;
import edu.esprit.kaddem.services.AuthenticationService;
import edu.esprit.kaddem.utils.JwtUtil;
import io.jsonwebtoken.impl.DefaultClaims;
import io.swagger.annotations.Api;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Api
@RestController
@CrossOrigin
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private AuthenticationService userDetailsService;

    @PostConstruct
    public void init() {
        etudiantMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull()).setSkipNullEnabled(true);
    }

    private static final ModelMapper etudiantMapper = new ModelMapper();


    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthReqDto authenticationRequest) {
        try {
            var authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (DisabledException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is disabled");
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResDto(token));
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody EtudiantDto user) {
        var etudiant = etudiantMapper.map(user, Etudiant.class);
        var saved = userDetailsService.save(etudiant);
        var etudiantDto = etudiantMapper.map(saved, EtudiantDto.class);
        return ResponseEntity.ok(etudiantDto);
    }

    @RequestMapping(value = "/refreshtoken", method = RequestMethod.GET)
    public ResponseEntity<?> refreshtoken(HttpServletRequest request) {
        DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");
        Map<String, Object> expectedMap = new HashMap<>(claims);
        String token = jwtTokenUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
        return ResponseEntity.ok(new AuthResDto(token));
    }
}