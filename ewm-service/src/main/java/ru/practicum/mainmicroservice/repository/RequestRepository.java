package ru.practicum.mainmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.mainmicroservice.model.Event;
import ru.practicum.mainmicroservice.model.ParticipationRequest;
import ru.practicum.mainmicroservice.model.Status;
import ru.practicum.mainmicroservice.model.User;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<ParticipationRequest, Long> {
    Integer countByEventIdAndStatus(Long eventId, Status status);

    List<ParticipationRequest> findAllByEventIdAndEventInitiatorId(Long eventId, Long userId);

    List<ParticipationRequest> findAllByRequester(User user);

    ParticipationRequest findByIdAndRequester(Long requestId, User user);

    int countByEventAndStatus(Event event, Status status);

    Boolean existsByRequesterIdAndEventId(Long userId, Long eventId);

    int countAllByEventIdAndStatus(Long eventId, Status status);

    List<ParticipationRequest> findAllByEventId(Long eventId);

}
