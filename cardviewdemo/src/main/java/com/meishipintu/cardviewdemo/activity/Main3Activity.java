package com.meishipintu.cardviewdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.meishipintu.cardviewdemo.adapter.MyAdapter;
import com.meishipintu.cardviewdemo.bean.Data;
import com.meishipintu.cardviewdemo.R;
import com.meishipintu.cardviewdemo.view.MyCardStackRecyclerView;
import com.meishipintu.cardviewdemo.view.MyLayoutManager;

import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private List<Data> dataList;
    private MyCardStackRecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        rv = (MyCardStackRecyclerView) findViewById(R.id.rv);
        dataList = (List<Data>) getIntent().getExtras().get("data");
        initRv();
    }

    private void initRv() {
        rv.setLayoutManager(new MyLayoutManager(dataList.size(), 100));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(new MyAdapter(Main3Activity.this, dataList));
    }

    public void remove(View view) {
        rv.removeTop();
    }
}
