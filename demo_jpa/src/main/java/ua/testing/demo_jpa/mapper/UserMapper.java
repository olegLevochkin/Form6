package ua.testing.demo_jpa.mapper;

import org.springframework.stereotype.Component;
import ua.testing.demo_jpa.dto.UserDTO;
import ua.testing.demo_jpa.entity.RoleType;
import ua.testing.demo_jpa.entity.User;

@Component
public class UserMapper {

    public User dtoToUser(UserDTO userDto) {
        final User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());

        return user;
    }

    public UserDTO userToDto(User user) {
        final UserDTO userDto = new UserDTO();
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setRole(RoleType.ROLE_USER);

        return userDto;
    }
}

