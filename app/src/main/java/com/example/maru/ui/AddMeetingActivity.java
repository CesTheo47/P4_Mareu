package com.example.maru.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maru.R;
import com.example.maru.databinding.ActivityCreateMeetingBinding;
import com.example.maru.di.DI;
import com.example.maru.service.MeetingApiService;
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog;
import com.github.dhaval2404.colorpicker.listener.ColorListener;
import com.github.dhaval2404.colorpicker.model.ColorShape;
import com.github.dhaval2404.colorpicker.model.ColorSwatch;

import org.jetbrains.annotations.NotNull;

public class AddMeetingActivity extends AppCompatActivity {


    private ActivityCreateMeetingBinding binding;
    private MeetingApiService apiService;

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
