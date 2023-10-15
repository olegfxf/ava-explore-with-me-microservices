package ru.practicum.mainmicroservice.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainmicroservice.dto.NewUserRequest;
import ru.practicum.mainmicroservice.dto.UserDto;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.service.impl.AdminUserService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/users")
public class AdminUserController {
    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto saveUser(@Valid @RequestBody NewUserRequest newUserRequest) {
        log.debug(String.valueOf(LogMessages.TRY_ADD), "ПОЛЬЗОВАТЕЛЯ");
        return adminUserService.saveUser(newUserRequest);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        log.debug(String.valueOf(LogMessages.TRY_REMOVE_OBJECT), "ПОЛЬЗОВАТЕЛЬ");
        adminUserService.deleteUser(id);
    }

    @GetMapping
    public List<UserDto> getAllUser(@RequestParam(required = false) List<Long> ids,
                                    @RequestParam(defaultValue = "0") int from,
                                    @RequestParam(defaultValue = "10") int size) {

        log.debug(String.valueOf(LogMessages.GET_ALL), "ПОЛЬЗОВАТЕЛЕЙ");
        return adminUserService.getAllUser(ids, from, size);
    }


}
