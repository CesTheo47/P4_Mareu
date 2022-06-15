package com.example.maru.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.maru.R;
import com.example.maru.databinding.ItemMeetingBinding;
import com.example.maru.model.Meeting;

import java.text.SimpleDateFormat;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh'h'mm");


    public MainAdapter(List<Meeting> items) {
        mMeetings = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMeetingBinding binding = ItemMeetingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        StringBuilder sb = new StringBuilder();
        for (String email : meeting.getEmailList()){
            sb.append(email + ", ");
        }
        holder.binding.itemMeetingFirstLine.setText(meeting.getName() + " - " + simpleDateFormat.format(meeting.getDate()) + " - " + meeting.getRoom());
        holder.binding.itemMeetingSecondLine.setText(sb.toString());
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       public ItemMeetingBinding binding;

        public ViewHolder(ItemMeetingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
