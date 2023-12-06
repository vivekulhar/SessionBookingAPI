package dev.vivek.sessionbookingapp1.exceptions;

public class MeetingNotFoundException extends Exception {
    public MeetingNotFoundException(String meetingNotFound) {
        super(meetingNotFound);
    }
}
