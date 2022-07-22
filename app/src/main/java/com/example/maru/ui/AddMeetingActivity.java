package com.example.maru.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maru.databinding.ActivityCreateMeetingBinding;
import com.example.maru.di.DI;
import com.example.maru.model.Room;
import com.example.maru.service.MeetingApiService;
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog;
import com.github.dhaval2404.colorpicker.listener.ColorListener;
import com.github.dhaval2404.colorpicker.model.ColorShape;
import com.github.dhaval2404.colorpicker.model.ColorSwatch;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddMeetingActivity extends AppCompatActivity {


    private ActivityCreateMeetingBinding binding;
    private MeetingApiService apiService;
    private int selectedColor = 0;
    private List<String> emailList = new ArrayList();
    private Spinner roomsSpinner;
    private Button btnDatePicker;
    private Button btnTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCreateMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiService = DI.getMeetingApiService();

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
                            }
                        })
                        .show();

            }
        });


        binding.emailAdd.setOnClickListener(new View.OnClickListener() {

            private boolean checkIfEmailIsValid(CharSequence email) {
                return Patterns.EMAIL_ADDRESS.matcher(email).matches();
            }

            EditText emailid = (EditText) binding.editEmail;
            String getEmailId = emailid.getText().toString();

            @Override
            public void onClick(View v) {
                if (checkIfEmailIsValid(getEmailId)){
                    emailList.add(binding.editEmail.getText().toString());
                    binding.editEmail.setText("");
                    updateEmailList();
                }
            }
        });

        /*binding.roomsSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rooms_spinner.setAdapter(new ArrayAdapter<Room>(this, binding.roomsSpinner, Room.values()));
            }
        }); */

        binding.btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    private void updateEmailList() {
        StringBuilder sb = new StringBuilder();
        for (String email : emailList){
            sb.append(email + ", ");
        }
        binding.txtEmailList.setText(sb.toString());
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
