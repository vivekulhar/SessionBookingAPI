package dev.vivek.sessionbookingapp1.services;


import dev.vivek.sessionbookingapp1.dtos.MeetingStatus;
import dev.vivek.sessionbookingapp1.dtos.RebookResponseDto;
import dev.vivek.sessionbookingapp1.dtos.ReebookRequestDto;
import dev.vivek.sessionbookingapp1.dtos.RescheduleRequestDto;
import dev.vivek.sessionbookingapp1.exceptions.MeetingNotFoundException;
import dev.vivek.sessionbookingapp1.models.Meeting;
import dev.vivek.sessionbookingapp1.repositories.MeetingRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MeetingServiceTest {

    @Mock
    MeetingRepository meetingRepository;
    @InjectMocks
    MeetingService meetingService;
    @Test
    void cancelMeeting() throws MeetingNotFoundException {
        Optional<Meeting> meeting = Optional.of(new Meeting());
        meeting.get().setId(1L);
        meeting.get().setStatus(MeetingStatus.BOOKED);
        meeting.get().setBookedAt(LocalDateTime.now());
        meeting.get().setDate(LocalDate.now().plusWeeks(1));
        meeting.get().setTime(LocalTime.now());
        when(meetingRepository.findById(anyLong())).thenReturn(meeting);

        when(meetingRepository.save(any())).thenReturn(meeting.get());

        when(meetingRepository.save(any())).thenReturn(meeting.get());
        assertEquals(MeetingStatus.CANCELLED.toString(), meetingService.cancelMeeting(1L).getStatus());

    }

    @Test
    void rescheduleMeeting() throws MeetingNotFoundException {
        Optional<Meeting> meeting = Optional.of(new Meeting());
        meeting.get().setId(1L);
        meeting.get().setStatus(MeetingStatus.BOOKED);
        meeting.get().setBookedAt(LocalDateTime.now());
        meeting.get().setDate(LocalDate.now().plusWeeks(1));

        meeting.get().setTime(LocalTime.now());
        when(meetingRepository.findById(anyLong())).thenReturn(meeting);

        when(meetingRepository.save(any())).thenReturn(meeting.get());

        assertEquals(MeetingStatus.RESCHEDULED.toString(), meetingService.rescheduleMeeting(new RescheduleRequestDto(1l, LocalDateTime.now().toString())).getStatus());
    }

    @Test
    void rebookMeeting() throws ParseException {
        Optional<List<Meeting>> meetings = Optional.of(List.of(new Meeting()));
        meetings.get().get(0).setId(1L);
        meetings.get().get(0).setStatus(MeetingStatus.BOOKED);
        meetings.get().get(0).setBookedAt(LocalDateTime.now());
        meetings.get().get(0).setDate(LocalDate.now().plusWeeks(1));
        meetings.get().get(0).setTime(LocalTime.now());
        when(meetingRepository.findByClientIdAndConsultantId(any(), any())).thenReturn(meetings);
        when(meetingRepository.save(any())).thenReturn(meetings.get().get(0));
        ReebookRequestDto requestDto = new ReebookRequestDto();
        requestDto.setClientId(1L);
        requestDto.setConsultantId(2L);
        requestDto.setStartDate("2023-12-01");
        requestDto.setEndDate("2023-12-06");
        requestDto.setStartTime("09:00:00");
        requestDto.setFrequency("weekly");
        List<RebookResponseDto> meetings1 = RebookResponseDto.toRebookResponseDto(meetings.get());

        assertEquals(meetings1.get(0).getDate(), meetingService.rebookMeeting(requestDto).get(0).getDate());
    }
}