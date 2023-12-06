package dev.vivek.sessionbookingapp1.dtos;


import dev.vivek.sessionbookingapp1.models.Meeting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RebookResponseDto {
    private Long id;
    private String date;
    private String time;
    public static List<RebookResponseDto> toRebookResponseDto(List<Meeting> meetings) {
        List<RebookResponseDto> rebookResponseDtos = new ArrayList<>();
        for (Meeting meeting : meetings) {
            RebookResponseDto rebookResponseDto = new RebookResponseDto();
            rebookResponseDto.setId(meeting.getId());
            rebookResponseDto.setDate(meeting.getDate().toString());
            rebookResponseDto.setTime(meeting.getTime().toString());
            rebookResponseDtos.add(rebookResponseDto);
        }
        return rebookResponseDtos;
    }
}
