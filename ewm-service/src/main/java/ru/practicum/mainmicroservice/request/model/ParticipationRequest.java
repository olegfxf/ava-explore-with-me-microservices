package ru.practicum.mainmicroservice.request.model;


import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.mainmicroservice.event.model.Event;
import ru.practicum.mainmicroservice.user.model.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "requests")
public class ParticipationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "event_id")
    Event event;


    @ManyToOne
    @JoinColumn(name = "requester_id")
    User requester;

//    @Enumerated(EnumType.STRING)
//    private Status status;

//    @Enumerated(EnumType.STRING)
//    private StatusRequest status;
}
