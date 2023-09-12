package ru.practicum.mainmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.mainmicroservice.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
