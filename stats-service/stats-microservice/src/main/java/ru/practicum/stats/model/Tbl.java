package ru.practicum.stats.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl")
public class Tbl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
}
