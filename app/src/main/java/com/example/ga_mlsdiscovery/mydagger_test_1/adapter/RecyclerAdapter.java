package com.example.ga_mlsdiscovery.mydagger_test_1.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.ga_mlsdiscovery.mydagger_test_1.R;
import com.example.ga_mlsdiscovery.mydagger_test_1.model.Passtime;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Passtime> mPasstimes ;

    public RecyclerAdapter(List<Passtime> passTimes){
        mPasstimes = passTimes;
    }

    public void setNewValue(List<Passtime> passTimes){
        mPasstimes = passTimes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.iss_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ((ViewHolder) viewHolder).timeStamp.setText(mPasstimes.get(i).getTimeStamp());

        ((ViewHolder) viewHolder).duration.setText(mPasstimes.get(i).getDuration());
    }

    @Override
    public int getItemCount() {
        return mPasstimes == null ? 0 : mPasstimes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView timeStamp;
        private TextView duration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timeStamp =  itemView.findViewById(R.id.time_stamp);
            duration =  itemView.findViewById(R.id.duration);
        }
    }
}
