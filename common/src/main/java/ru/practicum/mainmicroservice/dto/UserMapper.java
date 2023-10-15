package ru.practicum.mainmicroservice.dto;

import lombok.experimental.UtilityClass;
import ru.practicum.mainmicroservice.dto.user.NewUserRequest;
import ru.practicum.mainmicroservice.dto.user.UserDto;
import ru.practicum.mainmicroservice.dto.user.UserShortDto;
import ru.practicum.mainmicroservice.model.User;

@UtilityClass
public class UserMapper {

    public User toUser(NewUserRequest userDto) {
        return User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .build();
    }

    public UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public UserShortDto toUserShortDto(User user) {
        return UserShortDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public User toUserDto(NewUserRequest newUserRequest) {
        return User.builder()
                .name(newUserRequest.getName())
                .email(newUserRequest.getEmail())
                .build();
    }


}
