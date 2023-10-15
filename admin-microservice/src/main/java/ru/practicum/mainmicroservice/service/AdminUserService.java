package ru.practicum.mainmicroservice.service;

import ru.practicum.mainmicroservice.dto.user.NewUserRequest;
import ru.practicum.mainmicroservice.dto.user.UserDto;

import java.util.List;

public interface AdminUserService {
    UserDto saveUser(NewUserRequest newUserRequest);

    void deleteUser(Long id);

    List<UserDto> getAllUser(List<Long> ids, Integer from, Integer size);

}
