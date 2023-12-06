package dev.vivek.sessionbookingapp1.services;


import dev.vivek.sessionbookingapp1.dtos.*;
import dev.vivek.sessionbookingapp1.exceptions.MeetingNotFoundException;
import dev.vivek.sessionbookingapp1.models.Meeting;
import dev.vivek.sessionbookingapp1.repositories.MeetingRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class MeetingService {
    MeetingRepository meetingRepository;
    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }
    public MeetingResponseDTO cancelMeeting(Long meetingId) throws MeetingNotFoundException {
        Optional<Meeting> meeting = meetingRepository.findById(meetingId);
        if(meeting.isEmpty()){
            throw new MeetingNotFoundException("Meeting not found");
        }
        Meeting meetingToCancel = meeting.get();
        if(!checkTimeDifference(meetingToCancel)) {
            throw new MeetingNotFoundException("Meeting cannot be cancelled");
        }
        meetingToCancel.setStatus(MeetingStatus.CANCELLED);
        meetingRepository.save(meetingToCancel);

        return MeetingResponseDTO.fromMeeting(meetingToCancel);
    }
    private boolean checkTimeDifference(Meeting meeting) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime meetingTime = LocalDateTime.of(meeting.getDate(), meeting.getTime());
        return meetingTime.minusHours(12).isAfter(currentTime);
    }

    public MeetingRescheduledResponseDto rescheduleMeeting(RescheduleRequestDto rescheduleRequestDto) throws MeetingNotFoundException{
        Optional<Meeting> meeting = meetingRepository.findById(rescheduleRequestDto.getMeetingId());
        if(meeting.isEmpty()){
            throw new MeetingNotFoundException("Meeting not found");
        }
        Meeting meetingToReschedule = meeting.get();
        if(!checkReschedulePossibility(meetingToReschedule)) {
            throw new MeetingNotFoundException("Meeting cannot be rescheduled");
        }
        meetingToReschedule.setBookedAt(LocalDateTime.parse(rescheduleRequestDto.getNewBookingDate()));
        meetingToReschedule.setStatus(MeetingStatus.RESCHEDULED);
        meetingToReschedule = meetingRepository.save(meetingToReschedule);

        MeetingRescheduledResponseDto meetingResponseDTO = MeetingRescheduledResponseDto.fromMeeting(meetingToReschedule);
        return meetingResponseDTO;
    }

    private boolean checkReschedulePossibility(Meeting meetingToReschedule) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime meetingTime = LocalDateTime.of(meetingToReschedule.getDate(), meetingToReschedule.getTime());
        return meetingTime.minusHours(4).isAfter(currentTime);
    }

    public List<RebookResponseDto> rebookMeeting(ReebookRequestDto reebookRequestDto) throws ParseException {

        LocalDate startDate = LocalDate.parse(reebookRequestDto.getStartDate());
        LocalDate endDate = LocalDate.parse(reebookRequestDto.getEndDate());
        LocalTime startTime = LocalTime.parse(reebookRequestDto.getStartTime());
        String frequency = reebookRequestDto.getFrequency();
        int frequencyFactor = 0;

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            Meeting meeting = new Meeting();
            meeting.setClientId(reebookRequestDto.getClientId());
            meeting.setConsultantId(reebookRequestDto.getConsultantId());
            meeting.setDate(date);
            meeting.setTime(startTime);
            meeting.setStatus(MeetingStatus.BOOKED);
            meeting.setBookedAt(LocalDateTime.now());
            meetingRepository.save(meeting);

            if (frequency.equals("weekly")) {
                date = date.plusWeeks(1);
            } else if (frequency.equals("bi-weekly")) {
                date = date.plusWeeks(2);
            }
        }
        Optional<List<Meeting>> meetings = meetingRepository.findByClientIdAndConsultantId(reebookRequestDto.getClientId(), reebookRequestDto.getConsultantId());
        List<RebookResponseDto> rebookResponseDtos = RebookResponseDto.toRebookResponseDto(meetings.get());
        return rebookResponseDtos;
    }
}
