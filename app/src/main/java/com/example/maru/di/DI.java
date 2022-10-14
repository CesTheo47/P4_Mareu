package com.example.maru.di;


import com.example.maru.service.DummyMeetingApiService;
import com.example.maru.service.MeetingApiService;

public class DI {

    private static MeetingApiService service;

    // Singleton
    public static MeetingApiService getMeetingApiService() {
        if (service == null) {
            service = new DummyMeetingApiService();
        }
        return service;
    }

    // For Testing
    public static MeetingApiService getMeetingApiServiceNewInstance() {
        return new DummyMeetingApiService();
    }
}
