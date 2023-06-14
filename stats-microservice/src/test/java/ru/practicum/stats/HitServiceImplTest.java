package ru.practicum.stats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import ru.practicum.stats.dto.HitDto;
import ru.practicum.stats.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("ci,test")
class HitServiceImplTest {
    Hit hit = new Hit();

    @Autowired
    private HitRepository hitRepository;

    @Autowired
    private HitService hitService;

    LocalDateTime localDateTime = LocalDateTime.of(2020, 05, 05, 00, 00);
    LocalDateTime localDateTimePlus = localDateTime.plusMonths(1);
    LocalDateTime localDateTimeMinus = localDateTime.minusMonths(1);


    @BeforeEach
    void setUp() {
        hit = Hit.builder()
                .uri("https://museum.ru")
                .ip("77.5.10.66")
                .app("main-service")
                .timestamp(LocalDateTime.of(2020, 05, 05, 00, 00))
                .build();
    }


    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    @Test
    void save() {
        hitService.save(hit);
        assertEquals(1, hitRepository.findAll().size());
    }

    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    @Test
    void getStats() {

        hitService.save(hit);
        hit.setId(null);
        hit.setTimestamp(localDateTimePlus);
        hitService.save(hit);
        hit.setId(null);
        hit.setTimestamp(localDateTimeMinus);
        hitService.save(hit);

        System.out.println(hitRepository.findAll().size());

//"2020-05-05T00:00:00"

        List<HitDto> hitDtos = hitService.getStats("2020-03-05 00:00:00", "2020-07-05 00:00:00", true);
        assertEquals(1, hitDtos.size());

        hitDtos = hitService.getStats("2020-05-10 00:00:00", null, true);
        assertEquals(1, hitDtos.size());

        hitDtos = hitService.getStats(null, "2020-05-10 00:00:00", true);
        assertEquals(1, hitDtos.size());


        hitDtos = hitService.getStats("2020-03-05 00:00:00", "2020-07-05 00:00:00", false);
        assertEquals(3, hitDtos.size());

        hitDtos = hitService.getStats("2020-05-10 00:00:00", null, false);
        assertEquals(1, hitDtos.size());

        hitDtos = hitService.getStats(null, "2020-05-10 00:00:00", false);
        assertEquals(2, hitDtos.size());


    }
}