package dev.vivek.sessionbookingapp1.controllers;


import dev.vivek.sessionbookingapp1.dtos.MeetingRescheduledResponseDto;
import dev.vivek.sessionbookingapp1.dtos.MeetingResponseDTO;
import dev.vivek.sessionbookingapp1.dtos.RebookResponseDto;
import dev.vivek.sessionbookingapp1.services.MeetingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MeetingController.class)
class MeetingControllerTest {
    @MockBean
    MeetingService meetingService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void cancelMeeting() throws Exception {
        MeetingResponseDTO meetingResponseDTO = new MeetingResponseDTO();
        meetingResponseDTO.setId(1L);
        meetingResponseDTO.setStatus("CANCELLED");

        when(meetingService.cancelMeeting(anyLong())).thenReturn(meetingResponseDTO);
        mockMvc.perform(get("/meeting/cancel?meetingId=1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"status\":\"CANCELLED\"}"));
    }

    @Test
    void rescheduleMeeting() throws Exception {
        MeetingRescheduledResponseDto meetingRescheduledResponseDto = new MeetingRescheduledResponseDto();
        meetingRescheduledResponseDto.setId(1L);
        meetingRescheduledResponseDto.setStatus("RESCHEDULED");
        LocalDate date = LocalDate.of(2021, 8, 1);
        LocalTime time = LocalTime.of(10, 0, 0);
        LocalDateTime localDate = LocalDateTime.of(date, time);
        meetingRescheduledResponseDto.setBookedAt(localDate);
        when(meetingService.rescheduleMeeting(any())).thenReturn(meetingRescheduledResponseDto);
        mockMvc.perform(post("/meeting/reschedule")
                .contentType("application/json")
                .content("{\"meetingId\":1,\"newDateTime\":\"2021-08-01T10:00:00\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"status\":\"RESCHEDULED\",\"bookedAt\":\"2021-08-01T10:00:00\"}"));
    }

    @Test
    void rebookMeeting() throws Exception {
        List<RebookResponseDto> rebookResponseDtoList = List.of(new RebookResponseDto(1L, "2021-08-01", "10:00:00"));
        when(meetingService.rebookMeeting(any())).thenReturn(rebookResponseDtoList);
        mockMvc.perform(post("/meeting/recurring")
                .contentType("application/json")
                .content("{\"clientId\":1,\"consultantId\": 1,\"startDate\":\"2021-08-01\",\"endDate\":\"2021-08-01\",\"startTime\":\"10:00:00\",\"endTime\":\"11:00:00\",\"frequency\":\"WEEKLY\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"date\":\"2021-08-01\",\"time\":\"10:00:00\"}]"));
    }
}