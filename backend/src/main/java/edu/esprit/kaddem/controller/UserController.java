package edu.esprit.kaddem.controller;

import edu.esprit.kaddem.dto.AbstractUserDto;
import edu.esprit.kaddem.services.UserService;
import edu.esprit.kaddem.utils.PolymorphicUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
