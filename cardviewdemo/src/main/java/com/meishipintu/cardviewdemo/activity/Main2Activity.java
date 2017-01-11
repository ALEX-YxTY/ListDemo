package com.meishipintu.cardviewdemo.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.meishipintu.cardviewdemo.bean.Data;
import com.meishipintu.cardviewdemo.adapter.MyPagerAdapter;
import com.meishipintu.cardviewdemo.adapter.MyTransformer;
import com.meishipintu.cardviewdemo.R;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private ViewPager vp;
    private PagerAdapter adapter;
    private List<Data> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        vp = (ViewPager) findViewById(R.id.vp);
        dataList = (List<Data>) getIntent().getExtras().get("data");
        adapter = new MyPagerAdapter(this, dataList);
        vp.setAdapter(adapter);
        vp.setPageTransformer(false,new MyTransformer());
        vp.setOffscreenPageLimit(3);
        vp.setPageMargin(50);
        vp.setCurrentItem(100 - 100 % dataList.size());
    }
}
