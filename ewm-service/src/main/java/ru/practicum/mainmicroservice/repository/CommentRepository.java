package ru.practicum.mainmicroservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.mainmicroservice.model.Comment;
import ru.practicum.mainmicroservice.model.Event;
import ru.practicum.mainmicroservice.model.User;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

//    Page<Comment> findAllByUser(User user, Pageable pageable);
//
//    Optional<Comment> findByIdAndUserId(Long id, Long userId);

    Page<Comment> findAllByEvent(Event event, Pageable pageable);
}

