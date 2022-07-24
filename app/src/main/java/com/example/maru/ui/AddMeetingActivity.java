package com.example.maru.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maru.R;
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
    private int selectedColor = R.color.purple_500;
    private List<String> emailList = new ArrayList();

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
        /*binding.btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
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
            sb.append(email + ", ");
        }
        binding.txtEmailList.setText(sb.toString());
    }

    private void initSaveButton() {
        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormValid()) {
                    // Créer l'objet meeting avec tous les input de l'utilisateurs
                    // Revenir sur l'écran précédent
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


        return true;
    }





}
