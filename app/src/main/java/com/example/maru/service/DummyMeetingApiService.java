package com.example.maru.service;

import com.example.maru.model.Meeting;

import java.util.List;

public class DummyMeetingApiService {

    private List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }


}
