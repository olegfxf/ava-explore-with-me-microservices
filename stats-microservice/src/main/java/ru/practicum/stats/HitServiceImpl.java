package ru.practicum.stats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.stats.dto.HitDto;
import ru.practicum.stats.dto.HitMapper;
import ru.practicum.stats.model.Hit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HitServiceImpl implements HitService {
    HitRepository hitRepository;

    @Autowired
    public HitServiceImpl(HitRepository hitRepository) {
        this.hitRepository = hitRepository;
    }

    public Hit save(Hit hit) {
        return hitRepository.save(hit);
    }

    public List<HitDto> getStats(String startStr, String endStr, Boolean isUnique) {
        List<Hit> hits = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime start = LocalDateTime.now();
        if (startStr != null)
            start = LocalDateTime.parse(startStr, formatter);
        LocalDateTime end = LocalDateTime.now();
        if (endStr != null)
            end = LocalDateTime.parse(endStr, formatter);

        if (isUnique) {
            if (startStr != null && endStr == null) {
                hits = hitRepository.getHitDistinctAfter(start);
            } else if (startStr == null && endStr != null)
                hits = hitRepository.getHitDistinctBefor(end);
            else if (startStr != null && endStr != null) {
                hits = hitRepository.getHitDistinctBetween(start, end);
            }


        } else {
            if (startStr != null && endStr == null)
                hits = hitRepository.findAllByTimestampAfter(start);
            else if (startStr == null && endStr != null)
                hits = hitRepository.findAllByTimestampBefore(end);
            else if (startStr != null && endStr != null)
                hits = hitRepository.findAllByTimestampIsBetween(start, end);
        }

        System.out.println(hits);
        return hits.stream().map(hit -> HitMapper.toHitDto(hit)).collect(Collectors.toList());
    }
}
