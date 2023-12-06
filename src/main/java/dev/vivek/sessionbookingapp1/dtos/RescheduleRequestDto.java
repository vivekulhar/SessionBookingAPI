package dev.vivek.sessionbookingapp1.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RescheduleRequestDto {
    private Long meetingId;
    private String newBookingDate;
}
