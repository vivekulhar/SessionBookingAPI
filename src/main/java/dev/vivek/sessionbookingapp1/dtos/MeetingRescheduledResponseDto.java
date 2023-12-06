package dev.vivek.sessionbookingapp1.dtos;


import dev.vivek.sessionbookingapp1.models.Meeting;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class MeetingRescheduledResponseDto {
    private Long id;
    private String status;
    private LocalDateTime bookedAt;
    public static MeetingRescheduledResponseDto fromMeeting(Meeting meetingToReschedule) {
        MeetingRescheduledResponseDto meetingRescheduledResponseDto = new MeetingRescheduledResponseDto();
        meetingRescheduledResponseDto.setId(meetingToReschedule.getId());
        meetingRescheduledResponseDto.setStatus(meetingToReschedule.getStatus().toString());
        meetingRescheduledResponseDto.setBookedAt(meetingToReschedule.getBookedAt());
        return meetingRescheduledResponseDto;
    }
}
