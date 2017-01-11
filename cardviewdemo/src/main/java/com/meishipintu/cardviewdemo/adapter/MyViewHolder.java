package com.meishipintu.cardviewdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.meishipintu.cardviewdemo.R;

/**
 * Created by Administrator on 2017/1/4.
 */


public class MyViewHolder extends RecyclerView.ViewHolder {
    public ImageView iv;
    public TextView tv;
    public Button bt;

    public MyViewHolder(View itemView) {
        super(itemView);
        iv = (ImageView) itemView.findViewById(R.id.iv);
        tv = (TextView) itemView.findViewById(R.id.title);
        bt = (Button) itemView.findViewById(R.id.bt);
    }
}
