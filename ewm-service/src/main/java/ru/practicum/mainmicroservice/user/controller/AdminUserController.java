package ru.practicum.mainmicroservice.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.user.dto.UserDto;
import ru.practicum.mainmicroservice.user.dto.UserDtoReq;
import ru.practicum.mainmicroservice.user.service.AdminUserService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/users")
public class AdminUserController {
    AdminUserService adminUserService;

    @Autowired
    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @PostMapping
    public UserDto saveUser(@Valid @RequestBody UserDtoReq userDtoReq) {
        log.debug(String.valueOf(LogMessages.TRY_ADD), "user");
        return adminUserService.saveUser(userDtoReq);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        log.debug(String.valueOf(LogMessages.TRY_REMOVE_OBJECT), "Пользователь");
        adminUserService.deleteUser(id);
    }

    @GetMapping
    public List<UserDto> getAllUser(@RequestParam(required = false) List<Long> ids,
                                    @RequestParam(defaultValue = "0") int from,
                                    @RequestParam(defaultValue = "10") int size) {
        System.out.println(" WWW1");
        log.debug(String.valueOf(LogMessages.GET_ALL), "пользователей");
        return adminUserService.getAllUser(ids, from, size);
    }


}
