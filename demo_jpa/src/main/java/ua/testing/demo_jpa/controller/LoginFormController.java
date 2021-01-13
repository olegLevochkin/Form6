package ua.testing.demo_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.testing.demo_jpa.dto.UserDTO;
import ua.testing.demo_jpa.dto.UsersDTO;
import ua.testing.demo_jpa.service.UserService;

@Slf4j
@RestController
@RequestMapping(value = "/")
public class LoginFormController {

    private final UserService userService;

    @Autowired
    public LoginFormController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    //@RequestMapping(value = "login", method = RequestMethod.POST)
    @PostMapping(value = "login")
    public void loginFormController(UserDTO user) {
        log.info("{}", userService.findByUserLogin(user));
        log.info("{}", user);

/*       userService.saveNewUser(User.builder()
                .firstName("Ann")
                .lastName("Reizer")
                .email("AnnReizer@testing.ua")
                .role(RoleType.ROLE_USER)
                .build());*/
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public UsersDTO getAllUser() {
        log.info("{}", userService.getAllUsers());
        return userService.getAllUsers();
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDTO> showRegistrationForm(@RequestBody UserDTO userDto) {

        return ResponseEntity.ok(userService.saveUser(userDto));
    }
}
