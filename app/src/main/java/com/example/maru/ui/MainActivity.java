package com.example.maru.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.maru.R;
import com.example.maru.databinding.ActivityMainBinding;
import com.example.maru.di.DI;
import com.example.maru.event.DeleteMeetingEvent;
import com.example.maru.model.Meeting;
import com.example.maru.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MeetingApiService apiService;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiService = DI.getMeetingApiService();

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), AddMeetingActivity.class));
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initRecycler();
    }

    private void initRecycler() {
        adapter = new MainAdapter(apiService.getMeetings(), this);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.filter_date) {
            dateDialog();
            return true;
        }
        if (id == R.id.filter_room) {
            roomDialog();
            return true;
        }
        if (id == R.id.filter_reset) {
            resetFilters();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void roomDialog() {
    }

    private void resetFilters() {
        adapter.updateList(apiService.getMeetings());

    }

    private void dateDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_date_picker, null);
        dialogBuilder.setView(dialogView);

        Button btnStartDate = (Button) dialogView.findViewById(R.id.btn_start_date);
        btnStartDate.setText("Start Date");
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        // gerer le button

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        apiService.deleteMeeting(event.meeting);
        adapter.updateList(apiService.getMeetings());
    }

}