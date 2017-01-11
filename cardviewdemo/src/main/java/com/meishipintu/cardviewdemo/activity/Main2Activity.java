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
        //设置与滚动关联的动效需要实现Transformer类
        vp.setPageTransformer(false,new MyTransformer());
        vp.setOffscreenPageLimit(3);
        vp.setPageMargin(50);
        vp.setCurrentItem(100 - 100 % dataList.size());
    }
}
