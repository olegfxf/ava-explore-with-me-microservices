package ru.practicum.mainmicroservice.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainmicroservice.event.model.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event findFirstByCategoryId(Long id);
}
