package com.example.maru.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.maru.R;
import com.example.maru.databinding.ActivityCreateMeetingBinding;
import com.example.maru.di.DI;
import com.example.maru.model.Meeting;
import com.example.maru.model.Room;
import com.example.maru.service.MeetingApiService;
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog;
import com.github.dhaval2404.colorpicker.listener.ColorListener;
import com.github.dhaval2404.colorpicker.model.ColorShape;
import com.github.dhaval2404.colorpicker.model.ColorSwatch;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AddMeetingActivity extends AppCompatActivity {


    private ActivityCreateMeetingBinding binding;
    private MeetingApiService apiService;
    private int selectedColor;
    private List<String> emailList = new ArrayList();
    private boolean isDateSelected = false;
    private boolean isTimeSelected = false;

    private Calendar selectedDate = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCreateMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiService = DI.getMeetingApiService();

        initColorPicker();
        initSpinner();
        initDateTimePickers();
        initEmailList();
        initSaveButton();
    }


    private void initColorPicker() {
        selectedColor = ContextCompat.getColor(this, R.color.purple_500);
        binding.itemMeetingColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialColorPickerDialog.Builder(v.getContext())
                        .setTitle("Pick Theme")
                        .setColorShape(ColorShape.SQAURE)
                        .setColorSwatch(ColorSwatch._300)
                        .setColorListener(new ColorListener() {
                            @Override
                            public void onColorSelected(int color, @NotNull String colorHex) {
                                binding.itemMeetingColor.setColorFilter(color);
                                selectedColor = color;
                            }
                        })
                        .show();

            }
        });
    }

    private void initSpinner() {
        binding.roomsSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Room.values()));

        //Room selectedRoom = (Room) binding.roomsSpinner.getSelectedItem();
    }

    private void initDateTimePickers() {
        // Date
        binding.btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();

                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                int correctMonth = month + 1;
                                binding.btnDatePicker.setText(day + "/" + correctMonth + "/" + year);

                                selectedDate.set(Calendar.YEAR, year);
                                selectedDate.set(Calendar.MONTH, month);
                                selectedDate.set(Calendar.DAY_OF_MONTH, day);

                                isDateSelected = true;
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        //Time
        binding.btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();

                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(v.getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                binding.btnTimePicker.setText(String.format("%02d:%02d", hourOfDay, minute));

                                selectedDate.set(Calendar.HOUR, hourOfDay);
                                selectedDate.set(Calendar.MINUTE, minute);

                                isTimeSelected = true;
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.show();

            }
        });
    }

    private void initEmailList() {
        binding.emailAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIfEmailIsValid(binding.editEmail .getText().toString())){
                    emailList.add(binding.editEmail.getText().toString());
                    binding.editEmail.setText("");
                    updateEmailList();
                } else {
                    Toast.makeText(AddMeetingActivity.this, "Email invalide", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkIfEmailIsValid(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void updateEmailList() {
        StringBuilder sb = new StringBuilder();
        for (String email : emailList){
            sb.append(email);

            if (emailList.indexOf(email) != (emailList.size() -1)) {
                sb.append(", ");
            }
        }
        binding.txtEmailList.setText(sb.toString());
    }

    private void initSaveButton() {
        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormValid()) {
                    Meeting meeting = new Meeting(
                            binding.name.getText().toString(),
                            selectedDate.getTime(),
                            (Room) binding.roomsSpinner.getSelectedItem(),
                            emailList,
                            selectedColor
                    );
                    apiService.createMeeting(meeting);
                    onBackPressed();
                }
            }
        });
    }

    private boolean isFormValid() {
        if (binding.name.getText().toString().isEmpty()) {
            Toast.makeText(AddMeetingActivity.this, "Merci de renseigner un nom", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isDateSelected) {
            Toast.makeText(AddMeetingActivity.this, "Merci de renseigner une date", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isTimeSelected) {
            Toast.makeText(AddMeetingActivity.this, "Merci de renseigner une heure", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (emailList.isEmpty()) {
            Toast.makeText(AddMeetingActivity.this, "Merci de renseigner un email", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


}
