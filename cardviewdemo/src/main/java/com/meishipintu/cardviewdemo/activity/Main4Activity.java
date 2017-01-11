package com.meishipintu.cardviewdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.meishipintu.cardviewdemo.R;
import com.meishipintu.cardviewdemo.adapter.MyAdapter;
import com.meishipintu.cardviewdemo.bean.Data;
import com.meishipintu.cardviewdemo.view.MyCardStackRecyclerView;
import com.meishipintu.cardviewdemo.view.MyLayoutManager;

import java.util.List;

public class Main4Activity extends AppCompatActivity {

    private List<Data> dataList;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        rv = (RecyclerView) findViewById(R.id.rv);
        dataList = (List<Data>) getIntent().getExtras().get("data");
        initRv();
    }

    private void initRv() {
        rv.setLayoutManager(new GridLayoutManager(Main4Activity.this, 2));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(new MyAdapter(Main4Activity.this, dataList, 200));
    }
}
