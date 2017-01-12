package com.meishipintu.cardviewdemo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.meishipintu.cardviewdemo.bean.Data;
import com.meishipintu.cardviewdemo.adapter.MyPagerAdapter;
import com.meishipintu.cardviewdemo.adapter.MyTransformer;
import com.meishipintu.cardviewdemo.R;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private ViewPager vp;
    private RelativeLayout rl;
    private PagerAdapter adapter;
    private List<Data> dataList;
    private int[] colorArray;

    private int colorForeground,colorNext, colorCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        vp = (ViewPager) findViewById(R.id.vp);
        rl = (RelativeLayout) findViewById(R.id.activity_main2);
        colorArray = getResources().getIntArray(R.array.colorBackground);
        dataList = (List<Data>) getIntent().getExtras().get("data");
        adapter = new MyPagerAdapter(this, dataList);
        vp.setAdapter(adapter);
        //设置初始背景
        rl.setBackgroundColor(colorArray[0]);

        //设置与滚动关联的动效需要实现Transformer类
        vp.setPageTransformer(false,new MyTransformer());
        vp.setOffscreenPageLimit(3);
        vp.setPageMargin(50);
        vp.setCurrentItem(100 - 100 % dataList.size());
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i("test", "positionOffset:" + positionOffset);
                //positon 表示当前位置，positionOffset为[0,1)参数，表示滑动程度，positionOffsetPixels为上一个参数的像素表示
                //当前背景色
                colorForeground = colorArray[position % colorArray.length];
                Log.i("test", "colorForeground:" + Integer.toHexString(colorForeground));
                //下一个背景色
                colorNext = colorArray[(position + 1) % colorArray.length];
                Log.i("test", "colorNext:" + Integer.toHexString(colorNext));
                //计算rgb差值
                int deltRed = Color.red(colorNext) - Color.red(colorForeground);
                int deltGreen = Color.green(colorNext) - Color.green(colorForeground);
                int deltBlue = Color.blue(colorNext) - Color.blue(colorForeground);
                Log.i("test", "deltR:" + Integer.toHexString(deltRed) + ", deltG:" + Integer
                        .toHexString(deltGreen) + ", deltB:" + Integer.toHexString(deltBlue));
                //算出当前背景色
                int red = (int) (Color.red(colorForeground) + deltRed * positionOffset);
                int green = (int) (Color.green(colorForeground) + deltGreen * positionOffset);
                int blue = (int) (Color.blue(colorForeground) + deltBlue * positionOffset);
                Log.i("test", "currentR:" + Integer.toHexString(red) + ", currentG:" + Integer
                        .toHexString(green) + ", currentB:" + Integer.toHexString(blue));
                colorCurrent = Color.rgb(red, green, blue);
                Log.i("test", "colorCurrent:" + Integer.toHexString(colorCurrent));
                rl.setBackgroundColor(colorCurrent);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
