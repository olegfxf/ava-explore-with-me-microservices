package ru.practicum.stats.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ViewStats implements Comparable<ViewStats> {
    String app;
    String uri;
    Long hits;

    @Override
    public int compareTo(ViewStats viewStats) {
        return Integer.compare(Math.toIntExact(this.hits), Math.toIntExact(viewStats.hits));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewStats viewStats = (ViewStats) o;
        return Objects.equals(app, viewStats.app) && Objects.equals(uri, viewStats.uri) && Objects.equals(hits, viewStats.hits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(app, uri, hits);
    }

}
