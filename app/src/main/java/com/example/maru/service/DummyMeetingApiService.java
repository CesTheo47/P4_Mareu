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
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        startCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND, 0);
        startCalendar.set(Calendar.MILLISECOND, 0);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        endCalendar.set(Calendar.HOUR_OF_DAY, 23);
        endCalendar.set(Calendar.MINUTE, 59);
        endCalendar.set(Calendar.SECOND, 59);
        endCalendar.set(Calendar.MILLISECOND, 999);

        List<Meeting> mMeetingsFiltered = new ArrayList<>();
        for (Meeting meeting : meetings) {
            if (startCalendar.getTime().before(meeting.getDate()) && endCalendar.getTime().after(meeting.getDate())) {
                mMeetingsFiltered.add(meeting);
            }
        }

        return mMeetingsFiltered;
    }

    @Override
    public List<Meeting> getMeetingsByRoom(Room room) {
        List<Meeting> mMeetingsFiltered = new ArrayList<>();
        for (Meeting meeting : meetings) {
            if (room == meeting.getRoom()) {
                mMeetingsFiltered.add(meeting);
            }
        }
        return mMeetingsFiltered;
    }


    @Override
    public Meeting getMeeting(Long id) {
        return null;
    }

    @Override
    public boolean createMeeting(Meeting meeting) {

        for (Meeting m : meetings) {
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(m.getDate());
            startCalendar.add(Calendar.HOUR_OF_DAY, -1);
            Date d1 = startCalendar.getTime();
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(m.getDate());
            endCalendar.add(Calendar.HOUR_OF_DAY, 1);
            Date d2 = endCalendar.getTime();

            if (d1.before(meeting.getDate()) && d2.after(meeting.getDate())) {
                if (m.getRoom() == meeting.getRoom()) {
                    return false;
                }
            }
        }
        meetings.add(meeting);
        return true;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }


}
