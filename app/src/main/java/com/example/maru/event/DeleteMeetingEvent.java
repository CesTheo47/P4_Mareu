package com.example.maru.event;

import com.example.maru.model.Meeting;

public class DeleteMeetingEvent {

    public Meeting meeting;

    public DeleteMeetingEvent(Meeting meeting) {

        this.meeting = meeting;
    }
}
