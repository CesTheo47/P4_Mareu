package com.example.maru.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maru.R;
import com.example.maru.databinding.ActivityCreateMeetingBinding;
import com.example.maru.di.DI;
import com.example.maru.service.MeetingApiService;

public class AddMeetingActivity extends AppCompatActivity {


    private ActivityCreateMeetingBinding binding;
    private MeetingApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCreateMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiService = DI.getMeetingApiService();

    }




    /* public void onClick(View view) {
        if (view == binding.submitButton) {
            onSubmit();
        }
    } */

    private void onSubmit() {
        //String name = binding.textFieldRecipient.getEditText().getText().toString();
        // email,....

    }

}
