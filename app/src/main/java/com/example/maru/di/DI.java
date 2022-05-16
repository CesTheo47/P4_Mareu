package com.example.maru.di;


import com.example.maru.service.DummyMeetingApiService;
import com.example.maru.service.MeetingApiService;

public class DI {

    private static MeetingApiService service = (MeetingApiService) new DummyMeetingApiService();

    public static MeetingApiService getMeetingApiService() { return service; }

    public static MeetingApiService getNewInstanceApiService() {

        return (MeetingApiService) new DummyMeetingApiService();

    };
}
