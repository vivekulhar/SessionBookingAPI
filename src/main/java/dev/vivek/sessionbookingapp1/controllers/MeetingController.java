package dev.vivek.sessionbookingapp1.controllers;


import dev.vivek.sessionbookingapp1.dtos.*;
import dev.vivek.sessionbookingapp1.services.MeetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meeting")
public class MeetingController {
    MeetingService meetingService;
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }
    @GetMapping("/cancel")
    public ResponseEntity<MeetingResponseDTO> cancelMeeting(@RequestParam Long meetingId) throws Exception{
        MeetingResponseDTO meetingResponseDTO = meetingService.cancelMeeting(meetingId);
        return new ResponseEntity<>(meetingResponseDTO, null, 200);
    }

    @PostMapping("/reschedule")
    public ResponseEntity<MeetingRescheduledResponseDto> rescheduleMeeting(@RequestBody RescheduleRequestDto rescheduleRequestDto) throws Exception{
        MeetingRescheduledResponseDto meetingResponseDTO = meetingService.rescheduleMeeting(rescheduleRequestDto);
        return new ResponseEntity<>(meetingResponseDTO, null, 200);
    }

    @PostMapping("/recurring")
    public ResponseEntity<List<RebookResponseDto>> rebookMeeting(@RequestBody ReebookRequestDto reebookRequestDto) throws Exception{
        List<RebookResponseDto> meetingResponseDTO = meetingService.rebookMeeting(reebookRequestDto);

        return new ResponseEntity<>(meetingResponseDTO, null, 200);
    }


}
