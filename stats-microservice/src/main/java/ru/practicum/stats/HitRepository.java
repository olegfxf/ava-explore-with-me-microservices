package ru.practicum.stats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.stats.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HitRepository extends JpaRepository<Hit, Long> {
    List<Hit> findAllByTimestampAfter(LocalDateTime start);

    List<Hit> findAllByTimestampIsBetween(LocalDateTime start, LocalDateTime end);

    List<Hit> findAllByTimestampBefore(LocalDateTime end);

  //  List<Hit> findDistinctByTimestampAfter(LocalDateTime start);

    @Query(value = "SELECT  DISTINCT on (h.ip) h.id, h.app, h.uri,  h.ip, h.timestamp FROM hits AS h" +
            " WHERE h.timestamp > :startDate" +
            " ORDER  BY  h.ip ", nativeQuery = true)
    List<Hit> getHitDistinctAfter(@Param("startDate") LocalDateTime startDate);

    //List<Hit> findDistinctByTimestampIsBetween(LocalDateTime start, LocalDateTime end);

    @Query(value = "SELECT  DISTINCT on (h.ip) h.id, h.app, h.uri,  h.ip, h.timestamp FROM hits AS h" +
            " WHERE h.timestamp BETWEEN :startDate AND  :endDate" +
            " ORDER  BY  h.ip ", nativeQuery = true)
    List<Hit> getHitDistinctBetween(@Param("startDate") LocalDateTime startDate,
                                    @Param("endDate") LocalDateTime endDate);

    List<Hit> findDistinctByTimestampBefore(LocalDateTime end);

    @Query(value = "SELECT  DISTINCT on (h.ip) h.id, h.app, h.uri,  h.ip, h.timestamp FROM hits AS h" +
            " WHERE h.timestamp < :endDate" +
            " ORDER  BY  h.ip ", nativeQuery = true)
    List<Hit> getHitDistinctBefor(@Param("endDate") LocalDateTime endDate);
}
