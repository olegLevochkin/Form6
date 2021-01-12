package ua.testing.demo_jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.testing.demo_jpa.dto.UserDTO;
import ua.testing.demo_jpa.dto.UsersDTO;
import ua.testing.demo_jpa.entity.RoleType;
import ua.testing.demo_jpa.entity.User;
import ua.testing.demo_jpa.mapper.UserMapper;
import ua.testing.demo_jpa.repository.UserRepository;
import ua.testing.demo_jpa.validate.UserValidator;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserValidator userValidator;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userValidator = userValidator;
    }

    public UsersDTO getAllUsers() {
        //TODO checking for an empty user list
        return new UsersDTO(userRepository.findAll());
    }

    public User findByUserLogin(UserDTO userDTO) {
        //TODO check for user availability. password check
        return userRepository.findByEmail(userDTO.getEmail());
    }

    public UserDTO saveUser(UserDTO userDto) {
        userValidator.validatePassword(userDto.getPassword(), userDto.getMatchingPassword());
        userValidator.validateUser(userDto.getEmail(), userDto.getUsername());

        final String userPassword = userDto.getPassword();
        User user = userMapper.dtoToUser(userDto);
        user.setPassword(userPassword);
        user.setRole(RoleType.ROLE_USER);

        return userMapper.userToDto(userRepository.save(user));
    }


}
