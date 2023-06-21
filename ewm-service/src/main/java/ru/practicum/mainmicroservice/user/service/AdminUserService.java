package ru.practicum.mainmicroservice.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.mainmicroservice.exception.NotFoundException;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.user.dto.UserDto;
import ru.practicum.mainmicroservice.user.dto.UserDtoReq;
import ru.practicum.mainmicroservice.user.dto.UserMapper;
import ru.practicum.mainmicroservice.user.model.User;
import ru.practicum.mainmicroservice.user.repository.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class AdminUserService {
    UserRepository userRepository;

    @Autowired
    public AdminUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto saveUser(UserDtoReq userDtoReq) {
        log.debug(String.valueOf(LogMessages.ADD), "пользователь");
        System.out.println(userDtoReq);
        return UserMapper.toUserDto(userRepository.save(UserMapper.toUser(userDtoReq)));
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Пользователь с id "
                + id + " не найден"));
        log.debug(String.valueOf(LogMessages.REMOVE), "Ползователь");
        userRepository.delete(user);
    }

    public List<UserDto> getAllUser(List<Long> ids, Integer from, Integer size) {
        if (ids.isEmpty()) {
            log.debug(String.valueOf(LogMessages.GET_ALL), "пустой список");
            return Collections.EMPTY_LIST;
        } else {
            log.debug(String.valueOf(LogMessages.GET_ALL), "пользователи");
            Page<User>  users = userRepository.findAllByIdIn(ids, PageRequest.of(from / size, size));
            return users.stream().map(e -> UserMapper.toUserDto(e)).collect(Collectors.toList());
        }
    }
}
