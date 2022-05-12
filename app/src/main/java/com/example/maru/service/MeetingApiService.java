package com.example.maru.service;

import com.example.maru.model.Meeting;

import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeetings();

    Meeting getMeeting(Long id);
}
