package com.example.maru.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.maru.R;
import com.example.maru.databinding.ActivityMainBinding;
import com.example.maru.di.DI;
import com.example.maru.event.DeleteMeetingEvent;
import com.example.maru.model.Meeting;
import com.example.maru.model.Room;
import com.example.maru.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MeetingApiService apiService;
    private MainAdapter adapter;
    private Calendar selectedDate = Calendar.getInstance();

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
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_room_picker, null);
        dialogBuilder.setView(dialogView);

        Spinner spinnerRoom = (Spinner) dialogView.findViewById(R.id.rooms_spinner);
        spinnerRoom.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Room.values()));
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    private void resetFilters() {
        adapter.updateList(apiService.getMeetings());

    }

    private void dateDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_date_picker, null);
        dialogBuilder.setView(dialogView);

        Button btnStartDate = (Button) dialogView.findViewById(R.id.btn_start_date);
        Button btnEndDate = (Button) dialogView.findViewById(R.id.btn_end_date);
        //btnStartDate.setText("Date de début"); TODO in Layout

        Calendar beginDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        int mYear = today.get(Calendar.YEAR);
        int mMonth = today.get(Calendar.MONTH);
        int mDay = today.get(Calendar.DAY_OF_MONTH);

        btnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                int correctMonth = month + 1;
                                btnStartDate.setText(day + "/" + correctMonth + "/" + year);

                                beginDate.set(Calendar.YEAR, year);
                                beginDate.set(Calendar.MONTH, month);
                                beginDate.set(Calendar.DAY_OF_MONTH, day);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                int correctMonth = month + 1;
                                btnEndDate.setText(day + "/" + correctMonth + "/" + year);

                                endDate.set(Calendar.YEAR, year);
                                endDate.set(Calendar.MONTH, month);
                                endDate.set(Calendar.DAY_OF_MONTH, day);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        //TODO Btn submit check que les 2 ont été fait & la date de debut est avant la date de fin
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