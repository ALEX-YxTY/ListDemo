package com.meishipintu.cardviewdemo.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import com.meishipintu.cardviewdemo.R;
import com.meishipintu.cardviewdemo.adapter.MyAdapter;
import com.meishipintu.cardviewdemo.adapter.MyAdapterNew;
import com.meishipintu.cardviewdemo.adapter.MyViewHolder;
import com.meishipintu.cardviewdemo.bean.Data;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class Main5Activity extends AppCompatActivity {

    private List<Data> dataList;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        rv = (RecyclerView) findViewById(R.id.rv);
        initData();
    }

    private void initData() {
        dataList = new ArrayList<>();
        dataList.add(new Data("测试图片111111", R.drawable.ab, "it's ab pic "));
        dataList.add(new Data("测试图片777777", R.drawable.aa, "it's aa pic "));
        dataList.add(new Data("测试图片333333", R.drawable.ad, "it's ad pic "));
        dataList.add(new Data("测试图片444444", R.drawable.ae, "it's ae pic "));
        dataList.add(new Data("测试图片999999", R.drawable.cc, "it's cc pic "));
        dataList.add(new Data("测试图片555555", R.drawable.af, "it's af pic "));
        dataList.add(new Data("测试图片888888", R.drawable.bb, "it's bb pic "));
        dataList.add(new Data("测试图片666666", R.drawable.ag, "it's ag pic "));
        dataList.add(new Data("测试图片222222", R.drawable.dd, "it's dd pic "));

        rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(new MyAdapterNew(Main5Activity.this, dataList,100));
    }


}
