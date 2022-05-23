package com.example.maru.ui;

import androidx.fragment.app.Fragment;

import com.example.maru.model.Meeting;
import com.example.maru.service.MeetingApiService;

import java.util.List;

public class MeetingFragment extends Fragment {

    private MeetingApiService meetingApiService;
    private List<Meeting> meetings;


    public static MeetingFragment newInstance() {
        MeetingFragment fragment = new MeetingFragment();
        return fragment;
    }

    // ON create
    // On createView??
    // onResume
    // onStart
    // onStop
}
