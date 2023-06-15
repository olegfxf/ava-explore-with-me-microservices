package ru.practicum.stats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.stats.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("checkstyle:Regexp")
@Repository
public interface HitRepository extends JpaRepository<Hit, Long> {

    @Query(" SELECT new ru.practicum.stats.ViewStats(app, uri, COUNT(ip)) " +
            " FROM Hit " +
            " WHERE timestamp BETWEEN :start AND :end " +
            " GROUP BY app, uri"
    )
    List<ViewStats> getStatsWithoutUri(LocalDateTime start, LocalDateTime end);

    @Query(" SELECT new ru.practicum.stats.ViewStats(app, uri, COUNT(ip)) " +
            " FROM Hit " +
            " WHERE uri IN :uris AND timestamp BETWEEN :start AND :end " +
            " GROUP BY app, uri"
    )
    List<ViewStats> getStatsWithUri(LocalDateTime start, LocalDateTime end, List<String> uris);


    @Query("SELECT new ru.practicum.stats.ViewStats(app, uri, COUNT(DISTINCT ip)) " +
            " FROM Hit " +
            " WHERE timestamp BETWEEN :start AND :end " +
            " GROUP BY app, uri "
    )
    List<ViewStats> getStatsWithoutUriDistinct(LocalDateTime start, LocalDateTime end);

    @Query("SELECT new ru.practicum.stats.ViewStats(app, uri, COUNT(DISTINCT ip)) " +
            " FROM Hit " +
            " WHERE uri IN :uris AND timestamp BETWEEN :start AND :end " +
            " GROUP BY app, uri "
    )
    List<ViewStats> getStatsWithUriDistinct(LocalDateTime start, LocalDateTime end, List<String> uris);

}
