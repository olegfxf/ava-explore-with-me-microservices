package ru.practicum.stats;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.stats.dto.HitDto;
import ru.practicum.stats.model.Hit;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HitController.class)
class HitControllerTest {
    @Autowired
    ObjectMapper mapper;

    @MockBean
    private HitServiceImpl hitService;

    @Autowired
    private MockMvc mvc;

    private HitDto hitDto;
    private Hit hit = new Hit();


    @BeforeEach
    void setUp() {
        hitDto = HitDto.builder()
                .uri("https://museum.ru")
                .hit(6)
                .build();

        hit = hit.toBuilder()
                .id(1L)
                .uri("http://museum.ru")
                .ip("77.5.10.66")
                .timestamp(LocalDateTime.now())
                .app("main-service")
                .build();
    }


    @SneakyThrows
    @Test
    void save() {
        when(hitService.save(any()))
                .thenReturn(hit);

        mvc.perform(post("/hit")
                        .content(mapper.writeValueAsString(hit))
                        .header("x-sharer-user-id", 1L)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(hit.getId()), Long.class))
//                .andExpect(jsonPath("$.uri", is(hit.getUri())))
//                .andExpect(jsonPath("$.ip", is(hit.getIp())))
//                .andExpect(jsonPath("$.app", is(hit.getApp())))
//                .andExpect(jsonPath("$.timestamp", is(hit.getTimestamp()
//                        .toString().substring(0,28))));
                .andExpect(content().json(mapper.writeValueAsString(hit)));

    }

    @SneakyThrows
    @Test
    void getStats() {
        List<HitDto> hitDtos = List.of(hitDto);

        when(hitService.getStats(any(), any(), any()))
                .thenReturn(hitDtos);

        mvc.perform(get("/hit")
                        .param("start", "2020-05-05T00:00:00")
                        .param("end", "2020-06-05T00:00:00")
                        .param("isUnique", "false")
                        .content(mapper.writeValueAsString(hitDtos))
                        .header("x-sharer-user-id", 1L)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].uri", is(hitDtos.get(0).getUri())))
                .andExpect(jsonPath("$.[0].hit", is(hitDtos.get(0).getHit()), Integer.class));
        //          .andExpect(content().json(mapper.writeValueAsString(stat)));

    }
}