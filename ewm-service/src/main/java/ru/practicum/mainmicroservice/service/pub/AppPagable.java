package ru.practicum.mainmicroservice.service.pub;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class AppPagable extends PageRequest {
    protected AppPagable(int from, int size, Sort sort) {
        super(from / size, size, sort);
    }
}
