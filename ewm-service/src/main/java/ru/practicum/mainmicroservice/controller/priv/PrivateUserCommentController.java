package ru.practicum.mainmicroservice.controller.priv;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainmicroservice.dto.CommentDto;
import ru.practicum.mainmicroservice.service.priv.PrivateUserCommentService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/users/{userId}/comments")
public class PrivateUserCommentController {

    final PrivateUserCommentService privateUserCommentService;

    public PrivateUserCommentController(PrivateUserCommentService privateUserCommentService) {
        this.privateUserCommentService = privateUserCommentService;
    }

    @PostMapping("/{eventId}")
    public CommentDto saveComment(@Positive @PathVariable Long userId,
                                    @Positive @PathVariable Long eventId,
                                    @Valid @RequestBody CommentDto commentDto) {
        log.info("create comment by user {}", userId);
        return privateUserCommentService.saveComment(userId, eventId, commentDto);
    }


    @GetMapping
    public List<CommentDto> getAllCommentsByUser(@Positive @PathVariable Long userId,
                                                 @RequestParam(defaultValue = "0") int from,
                                                 @RequestParam(defaultValue = "10") int size) {
        log.info("get all user {} comments", userId);
        return privateUserCommentService.getAllCommentsByUser(userId, from, size);
    }


    @PatchMapping("/{commentId}")
    public CommentDto updateComment(@Positive @PathVariable Long commentId,
                                    @Positive @PathVariable Long userId,
                                    @Valid @RequestBody CommentDto commentDto) {
        log.info("update comment {}", commentId);
        return privateUserCommentService.updateComment(commentId, userId, commentDto);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@Positive @PathVariable Long userId,
                              @Positive @PathVariable Long commentId) {
        log.info("delete comment {}", commentId);
        privateUserCommentService.deleteComment(commentId, userId);
    }


}
