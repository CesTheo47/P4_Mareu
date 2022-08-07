package com.example.maru.service;

import com.example.maru.model.Meeting;
import com.example.maru.model.Room;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public List<Meeting> getMeetingsByDates(Date startDate, Date endDate) {

        List<Meeting> mMeetingsFiltered = new ArrayList<>();
        int size = meetings.size();
        for (int e = 0; e < size; e++) {
            Calendar mMeetingsCalendar = Calendar.getInstance();
            mMeetingsCalendar.setTime(meetings.get(e).getDate());
        }

        return meetings;
    }

    @Override
    public List<Meeting> getMeetingsByRoom(Room room) {
        //TODO
        return meetings;
    }


    @Override
    public Meeting getMeeting(Long id) {
        return null;
    }

    @Override
    public void createMeeting(Meeting meeting) { meetings.add(meeting); }

    @Override
    public void deleteMeeting(Meeting meeting) { meetings.remove(meeting); }


}
