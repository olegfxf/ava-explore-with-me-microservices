package ru.practicum.mainmicroservice.compilation.model;

import ru.practicum.mainmicroservice.event.model.Event;

import java.util.List;

public class Compilation {
    Long id;

    List<Event> events;

    Boolean pinned; //Закреплена ли подборка на главной странице сайта:  - true

    String title;
}
