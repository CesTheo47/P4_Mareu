package com.example.maru.service;

import com.example.maru.model.Meeting;

import java.util.List;

public interface MeetingApiService {

    //Get all my meeting
    List<Meeting> getMeetings();

    Meeting getMeeting(Long id);

    // Create meeting
    void createMeeting(Meeting meeting);

    // Delete meeting
    void deleteMeeting(Meeting meeting);
}
