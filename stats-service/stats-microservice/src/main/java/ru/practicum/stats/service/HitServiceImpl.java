package ru.practicum.stats.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.dto.HitDto;
import ru.practicum.stats.exception.DateException;
import ru.practicum.stats.HitRepository;
import ru.practicum.stats.dto.HitMapper;
import ru.practicum.stats.dto.ViewStats;
import ru.practicum.stats.model.Stats;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HitServiceImpl implements HitService {
    private final HitRepository hitRepository;

    @Autowired
    public HitServiceImpl(HitRepository hitRepository) {
        this.hitRepository = hitRepository;
    }

    public HitDto save(Stats stats) {

        log.debug("Выполнен запрос на сохренение события");
        System.out.println("ZZZ5 saveC на запись " + stats.getId() + "   " + stats.getApp() + "    " + stats.getUri());
        List<String> s1 = hitRepository.findAll().stream().map(e->e.getUri()).collect(Collectors.toList());
        Integer hits0 = s1.stream().filter(e->e.equals(stats.getUri())).collect(Collectors.toList()).size();
        HitDto hitDto = HitMapper.toHitDto(hitRepository.save(stats));
        System.out.println("ZZZ6  C заисано ////////////");
        System.out.println("hitDto" + hitDto);
        List<String> s2 = hitRepository.findAll().stream().map(e->e.getUri()).collect(Collectors.toList());
        Integer hits = s2.stream().filter(e->e.equals(stats.getUri())).collect(Collectors.toList()).size();
        System.out.println("hits = " + hits0 + "   " + hits);
        hitDto.setHit(hits);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<ViewStats> viewStats = getStats("2020-05-05 00:00:00", "2035-05-05 00:00:00", Optional.of(List.of("/events")), false);
        System.out.println("1HITS " + viewStats.get(0).getHits() );

        System.out.println(stats.getUri());
        hitRepository.findAll().stream().map(e->e.getUri()).filter(e->e== stats.getUri()).collect(Collectors.toList()).size();

        return hitDto;
    }


    public List<ViewStats> getStats(String startStr, String endStr, Optional<List<String>> uris, Boolean isUnique) {
        List<ViewStats> viewStats = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime start = LocalDateTime.parse(startStr, formatter);
        LocalDateTime end = LocalDateTime.parse(endStr, formatter);

        if (start == null || end == null || start.isAfter(end))
            throw new DateException("Дата старта должна быть раньше даты окончания");


        //                System.out.println(uris.get() + "  uris.get()");
//                if (uris.get().get(0).equals("/events"))
//                    viewStats = hitRepository.getStatsWithoutUri(start, end);
//                else


        if (isUnique) {
            if (uris.isPresent())
                viewStats = hitRepository.getStatsWithUriDistinct(start, end, uris.get());
            else
                viewStats = hitRepository.getStatsWithoutUriDistinct(start, end);

        } else {
            if (uris.isPresent()) {
                System.out.println("!!!");
                viewStats = hitRepository.getStatsWithUri(start, end, uris.get());
            }
            else
                viewStats = hitRepository.getStatsWithoutUri(start, end);
        }

        System.out.println("XXX2 Serv viewStats.size() = " + viewStats.size());
        viewStats.stream().forEach(System.out::println);

        Collections.sort(viewStats, (d1, d2) -> {
            return d2.getHits().intValue() - d1.getHits().intValue();
        });
        log.debug("Выполнен запрос на предоставление статистики");
        return viewStats;
    }


}
