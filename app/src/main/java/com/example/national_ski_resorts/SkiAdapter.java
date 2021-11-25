package com.example.national_ski_resorts;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SkiAdapter extends RecyclerView.Adapter<SkiAdapter.CustomViewHolder> {
    private ArrayList<SkiData> mList = null;
    private Activity context = null;

    public SkiAdapter(Activity context, ArrayList<SkiData> list) {
        this.context = context;
        this.mList = list;
    }
    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView attime;
        protected TextView starttime;
        protected TextView bar;
        protected TextView endtime;
        protected TextView texcost;

        public CustomViewHolder(@NonNull View view) {
            super(view);
            this.attime = (TextView) view.findViewById(R.id.textView_list_attime);
            this.starttime = (TextView) view.findViewById(R.id.textView_list_starttime);
            this.bar = (TextView) view.findViewById(R.id.textView_list_bar);
            this.endtime = (TextView) view.findViewById(R.id.textView_list_endtime);
            this.texcost = (TextView) view.findViewById(R.id.textView_list_texcost);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.skiitem_list, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {
        viewholder.attime.setText(mList.get(position).getSki_attime());
        viewholder.starttime.setText(mList.get(position).getSki_starttime());
        viewholder.bar.setText("~");
        viewholder.endtime.setText(mList.get(position).getSki_endtime());
        viewholder.texcost.setText(mList.get(position).getSki_texcost());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}
