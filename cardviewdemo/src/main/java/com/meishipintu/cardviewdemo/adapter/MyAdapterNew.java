package com.meishipintu.cardviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.meishipintu.cardviewdemo.R;
import com.meishipintu.cardviewdemo.bean.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/1/3.
 */

public class MyAdapterNew extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<Data> dataList;
    private int mCount;

    public MyAdapterNew(Context context, List<Data> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.mCount = dataList.size();
    }

    public MyAdapterNew(Context context, List<Data> dataList, int count) {
        this.context = context;
        this.dataList = dataList;
        this.mCount = count;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_getmi_task_new, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        position = position % dataList.size();
        final Data data = dataList.get(position);
        holder.tv.setText(data.getTitle());
        holder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, data.getClickShow(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.iv.setImageResource(data.getPicRes());
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    public List<Data> getDataList() {
        return this.dataList;
    }

    public int getmCount() {
        return this.mCount;
    }

    public void setItemCount(int count) {
        mCount = count;
    }
}
