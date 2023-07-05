package ru.practicum.mainmicroservice.controller.pub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainmicroservice.dto.CommentDto;
import ru.practicum.mainmicroservice.service.pub.PublicUserCommentService;

import javax.validation.constraints.Positive;
import java.util.List;

@Slf4j
@RequestMapping("/events/{eventId}/comments")
@RestController
public class PublicUserCommentController {
    PublicUserCommentService publicUserCommentService;

    public PublicUserCommentController(PublicUserCommentService publicUserCommentService) {
        this.publicUserCommentService = publicUserCommentService;
    }


    @GetMapping
    public List<CommentDto> getAllCommentsForEvent(@Positive @PathVariable Long eventId,
                                                   @RequestParam(defaultValue = "0") int from,
                                                   @RequestParam(defaultValue = "10") int size) {
        log.info("get all comments for event {}", eventId);
        return publicUserCommentService.getAllCommentsForEvent(eventId, from, size);
    }
}
