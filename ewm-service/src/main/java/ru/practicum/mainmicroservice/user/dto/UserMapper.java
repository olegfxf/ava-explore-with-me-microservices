package ru.practicum.mainmicroservice.user.dto;

import ru.practicum.mainmicroservice.user.model.User;

public class UserMapper {

    public static User toUser(UserDtoReq userDtoReq) {
        return User.builder()
                .name(userDtoReq.getName())
                .email(userDtoReq.getEmail())
                .build();
    }

    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public static UserShortDto toUserShortDto(User user) {
        return UserShortDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }


}
