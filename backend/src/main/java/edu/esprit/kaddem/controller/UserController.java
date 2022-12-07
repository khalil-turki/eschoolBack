package edu.esprit.kaddem.controller;

import edu.esprit.kaddem.dto.AbstractUserDto;
import edu.esprit.kaddem.model.user.Utilisateur;
import edu.esprit.kaddem.services.UserService;
import edu.esprit.kaddem.utils.PolymorphicUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<? extends AbstractUserDto<?>> getAllUsers() {
        var users = userService.getAllUsers();
        return users.stream().map(PolymorphicUtils::getDtoFromUser).toList();
    }

    @GetMapping("/{id}")
    public AbstractUserDto<?> getUserById(@PathVariable("id") Integer id) {
        if(id == null)
            id = ((Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        var user = userService.getUserById(id);
        return PolymorphicUtils.getDtoFromUser(user);
    }

    @PutMapping("/update2fa/{status}")
    public void update2FAStatus(@PathVariable boolean status) {
        var user = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.update2FAStatus(status, user.getId());
    }

    @GetMapping("/qrUrl")
    public String getQRUrl() {
        var user = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.generateQRUrl(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        userService.delete(id);
    }

    @PostMapping("/")
    public void addUser(@RequestBody AbstractUserDto<?> user) {
        Utilisateur u = PolymorphicUtils.getUserFromDto(user);
        userService.add(u);
    }
}
