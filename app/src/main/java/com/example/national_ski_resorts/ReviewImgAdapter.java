package com.example.national_ski_resorts;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewImgAdapter extends RecyclerView.Adapter<ReviewImgAdapter.CustomViewHolder> {
    private ArrayList<ReviewImgData> mList = null;
    private Activity context = null;

    public ReviewImgAdapter(Activity context, ArrayList<ReviewImgData> list){
        this.context = context;
        this.mList = list;
    }
    class CustomViewHolder extends RecyclerView.ViewHolder{
        protected ImageView img;

        public CustomViewHolder(@NonNull View view) {
            super(view);
            this.img = (ImageView) view.findViewById(R.id.imageView_list_img);
        }
    }


    @NonNull
    @Override
    public ReviewImgAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imageitem_list, null);
        ReviewImgAdapter.CustomViewHolder viewHolder = new ReviewImgAdapter.CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewImgAdapter.CustomViewHolder holder, int position) {
        holder.img.setImageBitmap(mList.get(position).getReview_img());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}
