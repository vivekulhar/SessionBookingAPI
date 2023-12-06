package dev.vivek.sessionbookingapp1.models;


import dev.vivek.sessionbookingapp1.dtos.MeetingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "meeting")
public class Meeting extends BaseModel{

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "consultant_id")
    private Long consultantId;

    @Column(name = "booked_at")
    private LocalDateTime bookedAt;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "date")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MeetingStatus status;

    // Getters and setters
}
