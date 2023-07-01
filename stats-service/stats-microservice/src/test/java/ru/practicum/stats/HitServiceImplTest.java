package ru.practicum.stats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import ru.practicum.stats.dto.ViewStats;
import ru.practicum.stats.model.Stats;
import ru.practicum.stats.service.HitService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("ci,test")
class HitServiceImplTest {
    Stats stats = new Stats();

    @Autowired
    private HitRepository hitRepository;

    @Autowired
    private HitService hitService;

    LocalDateTime localDateTime = LocalDateTime.of(2020, 05, 05, 00, 00);
    LocalDateTime localDateTimePlus = localDateTime.plusMonths(1);
    LocalDateTime localDateTimeMinus = localDateTime.minusMonths(1);

    Optional<List<String>> uris = Optional.of(new ArrayList<>());


    @BeforeEach
    void setUp() {
        stats = Stats.builder()
                .uri("/events")
                .ip("77.5.10.66")
                .app("main-service")
                .timestamp(LocalDateTime.of(2020, 05, 05, 00, 00))
                .build();

        uris.get().add("/events");
    }


    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    @Test
    void save() {
        hitService.save(stats);
        assertEquals(1, hitRepository.findAll().size());
    }

    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    @Test
    void getStats() {

        hitService.save(stats);
        stats.setId(null);
        stats.setApp("main-service1");
        stats.setTimestamp(localDateTimePlus);
        hitService.save(stats);
        stats.setId(null);
        stats.setApp("main-service2");
        stats.setTimestamp(localDateTimeMinus);
        hitService.save(stats);

        List<ViewStats> viewStatsList = hitService.getStats("2020-03-05 00:00:00", "2020-07-05 00:00:00", uris, true);
        assertEquals(3, viewStatsList.size());

        viewStatsList = hitService.getStats("2020-03-05 00:00:00", "2020-07-05 00:00:00", uris, false);
        assertEquals(3, viewStatsList.size());

    }
}