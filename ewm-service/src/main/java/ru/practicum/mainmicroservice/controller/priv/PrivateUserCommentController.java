package ru.practicum.mainmicroservice.controller.priv;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainmicroservice.dto.CommentDto;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.service.priv.PrivateUserCommentService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/users/{userId}/comments")
public class PrivateUserCommentController {

    private final PrivateUserCommentService privateUserCommentService;

    public PrivateUserCommentController(PrivateUserCommentService privateUserCommentService) {
        this.privateUserCommentService = privateUserCommentService;
    }

    @PostMapping("/{eventId}")
   // @ResponseStatus(HttpStatus.CREATED)
    public CommentDto saveComment(@Positive @PathVariable Long userId,
                                  @Positive @PathVariable Long eventId,
                                  @Valid @RequestBody CommentDto commentDto) {

        log.debug(String.valueOf(LogMessages.TRY_ADD), "КОММЕНТАРИЙ");
        return privateUserCommentService.saveComment(userId, eventId, commentDto);
    }


    @GetMapping("/events/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CommentDto> getAllCommentsByUser(@Positive @PathVariable Long userId,
                                                 @RequestParam(defaultValue = "0") int from,
                                                 @RequestParam(defaultValue = "10") int size) {

        log.debug(String.valueOf(LogMessages.TRY_GET_ALL), "КОММЕНТАРИИ ПОЛЬЗОВАТЕЛЯ");
        return privateUserCommentService.getAllCommentsByUser(userId, from, size);
    }


    @PatchMapping("/{commentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto updateComment(@Positive @PathVariable Long commentId,
                                    @Positive @PathVariable Long userId,
                                    @Valid @RequestBody CommentDto commentDto) {

        log.debug(String.valueOf(LogMessages.TRY_UPDATE), "КОММЕНТАРИЙ");
        return privateUserCommentService.updateComment(commentId, userId, commentDto);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@Positive @PathVariable Long userId,
                              @Positive @PathVariable Long commentId) {

        log.debug(String.valueOf(LogMessages.TRY_REMOVE_OBJECT), "КОММЕНТАРИЙ");
        privateUserCommentService.deleteComment(commentId, userId);
    }


}
