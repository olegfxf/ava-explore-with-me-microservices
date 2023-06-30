package ru.practicum.mainmicroservice.service.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.mainmicroservice.dto.NewUserRequest;
import ru.practicum.mainmicroservice.dto.UserDto;
import ru.practicum.mainmicroservice.dto.UserMapper;
import ru.practicum.mainmicroservice.exception.BadRequestException;
import ru.practicum.mainmicroservice.exception.NotFoundException;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.model.User;
import ru.practicum.mainmicroservice.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class AdminUserService {
    private final UserRepository userRepository;

    @Autowired
    public AdminUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDto saveUser(NewUserRequest newUserRequest) {
        String[] emailPart = newUserRequest.getEmail().split("@");
        if (emailPart[0].length() > 63)
            throw new BadRequestException("Длина имени пользователя больше 64");
        String[] domainPart = emailPart[1].split("\\.");
        if (emailPart[1].length() > 63 && newUserRequest.getEmail().length() != 254)
            throw new BadRequestException("Длина доменного имени больше 63");

        log.debug(String.valueOf(LogMessages.ADD), "ПОЛЬЗОВАТЕЛЬ");
        return UserMapper.toUserDto(userRepository.save(UserMapper.toUser(newUserRequest)));
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Пользователь с id "
                + id + " не найден"));

        log.debug(String.valueOf(LogMessages.REMOVE), "ПОЛЬЗОВАТЕЛЬ");
        userRepository.delete(user);
    }

    public List<UserDto> getAllUser(List<Long> ids, Integer from, Integer size) {
        Page<User> users;
        if (ids == null) {
            users = userRepository.findAll(PageRequest.of(from / size, size));
        } else {
            users = userRepository.findAllByIdIn(ids, PageRequest.of(from / size, size));
        }

        log.debug(String.valueOf(LogMessages.GET_ALL), "ПОЛЬЗОВАТЕЛЕЙ");
        return users.stream().map(e -> UserMapper.toUserDto(e)).collect(Collectors.toList());
    }
}
