package dev.vivek.sessionbookingapp1.dtos;


import dev.vivek.sessionbookingapp1.models.Meeting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingResponseDTO {
    private Long id;
    private String status;


    public static MeetingResponseDTO fromMeeting(Meeting meeting) {
        MeetingResponseDTO meetingResponseDTO = new MeetingResponseDTO();
        meetingResponseDTO.setId(meeting.getId());
        meetingResponseDTO.setStatus(meeting.getStatus().toString());
        return meetingResponseDTO;
    }
}
