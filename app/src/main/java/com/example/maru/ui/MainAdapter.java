package com.example.maru.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.maru.databinding.ItemMeetingBinding;
import com.example.maru.event.DeleteMeetingEvent;
import com.example.maru.model.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Meeting> mMeetings;
    private Context mContext;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH'h'mm");


    public MainAdapter(List<Meeting> items, Context context) {
        mMeetings = items;
        mContext = context;
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
        for (String email : meeting.getEmailList()) {
            sb.append(email);

            if (meeting.getEmailList().indexOf(email) != (meeting.getEmailList().size() - 1)) {
                sb.append(", ");
            }
        }
        holder.binding.itemMeetingFirstLine.setText(meeting.getName() + " - " + simpleDateFormat.format(meeting.getDate()) + " - " + meeting.getRoom());
        holder.binding.itemMeetingSecondLine.setText(sb.toString());
        holder.binding.itemMeetingDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });
        holder.binding.itemMeetingColor.setColorFilter(meeting.getColor());
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public void updateList(List<Meeting> meetingList) {
        mMeetings = meetingList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemMeetingBinding binding;

        public ViewHolder(ItemMeetingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
