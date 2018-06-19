package com.futuristicdevelopers.shamoon.controller;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.futuristicdevelopers.shamoon.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageViewPhoto;

    public MyViewHolder(View itemView) {
        super(itemView);
        imageViewPhoto = itemView.findViewById(R.id.iv_photos);
    }
}
