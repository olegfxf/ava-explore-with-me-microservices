package ru.practicum.mainmicroservice.service.pub;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class GetPagable extends PageRequest {
    protected GetPagable(int from, int size, Sort sort) {
        super(from / size, size, sort);
    }
}
