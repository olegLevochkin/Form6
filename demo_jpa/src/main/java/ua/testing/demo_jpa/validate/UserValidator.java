package ua.testing.demo_jpa.validate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ua.testing.demo_jpa.entity.User;
import ua.testing.demo_jpa.exception.ValidatorException;
import ua.testing.demo_jpa.repository.UserRepository;

import java.util.Objects;

@Component
public class UserValidator {

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validatePassword(String password, String matchingPassword) {
        if (!Objects.equals(password, matchingPassword)) {
            throw new ValidatorException("Passwords does not match");
        }
        if (password.length() < 4) {
            throw new ValidatorException("Password length must be minimum of 4 symbols");
        }
    }

    public void validateUser(String userEmail, String userName) {
        if (StringUtils.isBlank(userEmail)) {
            throw new ValidatorException("Email should be present");
        }
        if (StringUtils.isBlank(userName)) {
            throw new ValidatorException("Username should be present");
        }

        final User userByEmail = userRepository.findByEmail(userEmail);
        final User userByUsername = userRepository.findByUsername(userName);
        if (Objects.nonNull(userByEmail)) {
            throw new ValidatorException("Email already exists");
        }
        if (Objects.nonNull(userByUsername)) {
            throw new ValidatorException("Username already exists");
        }
    }
}

