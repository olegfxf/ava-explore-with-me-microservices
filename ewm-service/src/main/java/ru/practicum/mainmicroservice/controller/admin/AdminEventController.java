package ru.practicum.mainmicroservice.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainmicroservice.dto.EventFullDto;
import ru.practicum.mainmicroservice.dto.UpdateEventAdminRequest;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.service.impl.AdminEventService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping("/admin/events")
@RestController
public class AdminEventController {
    private final AdminEventService adminEventService;

    public AdminEventController(AdminEventService adminEventService) {
        this.adminEventService = adminEventService;
    }

    @PatchMapping("/{eventId}")
    public EventFullDto updateAdminEvents(@PathVariable Long eventId,
                                          @Valid @RequestBody UpdateEventAdminRequest adminRequest) {
        log.debug(String.valueOf(LogMessages.TRY_UPDATE), "СОБЫТИЯ");
        return adminEventService.updateAdminEvents(eventId, adminRequest);
    }

    @GetMapping
    public List<EventFullDto> getAdminEvents(@RequestParam(required = false) List<Long> users,
                                             @RequestParam(required = false) List<String> states,
                                             @RequestParam(required = false) List<Long> categories,
                                             @RequestParam(required = false) String rangeStart,
                                             @RequestParam(required = false) String rangeEnd,
                                             @RequestParam(defaultValue = "0") int from,
                                             @RequestParam(defaultValue = "10") int size) {
        log.info("get events by admin with param: userIds = {}, states = {}, categoryIds = {}, rangeStart = {}, " +
                "rangeEnd = {}, from = {}, size = {}", users, states, categories, rangeStart, rangeEnd, from, size);
        log.debug(String.valueOf(LogMessages.TRY_GET_ALL), "СОБЫТИЯ");
        return adminEventService.getAdminEvents(users, states, categories, rangeStart, rangeEnd, from, size);
    }

}
