package ru.practicum.mainmicroservice.controller.pub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainmicroservice.dto.CommentDto;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.service.pub.PublicUserCommentService;

import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RequestMapping("/events/{eventId}/comments")
@RestController
@Validated
public class PublicUserCommentController {
    private final PublicUserCommentService publicUserCommentService;

    public PublicUserCommentController(PublicUserCommentService publicUserCommentService) {
        this.publicUserCommentService = publicUserCommentService;
    }


    @GetMapping
    public List<CommentDto> getAllCommentsForEvent(@PositiveOrZero @PathVariable Long eventId,
                                                   @RequestParam(defaultValue = "0") int from,
                                                   @RequestParam(defaultValue = "10") int size) {

        log.debug(String.valueOf(LogMessages.TRY_GET_ALL), "КОММЕНТАРИИ ПО СОБЫТИЮ");
        return publicUserCommentService.getAllCommentsForEvent(eventId, from, size);
    }
}
