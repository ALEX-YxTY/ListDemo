package com.meishipintu.cardviewdemo.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import com.meishipintu.cardviewdemo.bean.Data;
import com.meishipintu.cardviewdemo.adapter.MyAdapter;
import com.meishipintu.cardviewdemo.adapter.MyViewHolder;
import com.meishipintu.cardviewdemo.R;
import com.meishipintu.cardviewdemo.view.MyLayoutManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Data> dataList;
    private boolean refresh = true;         //标识滚动的flag，以保证滚动矫正时不陷入循环
    private int displayWidth = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);
        initData();
    }

    private void initData() {
        dataList = new ArrayList<>();
        dataList.add(new Data("测试图片111111", R.drawable.aa, "it's a pic "));
        dataList.add(new Data("测试图片222222", R.drawable.bb, "it's b pic "));
        dataList.add(new Data("测试图片333333", R.drawable.cc, "it's c pic "));
        dataList.add(new Data("测试图片444444", R.drawable.dd, "it's d pic "));
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(layoutManager);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int first = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                if (newState == SCROLL_STATE_IDLE && refresh) {
                    Log.i("test", "first Item:" + first);
                    View childView = layoutManager.findViewByPosition(first);
                    int childWidth = childView.getWidth();
                    Log.i("test", "first Item width:" + childWidth);
                    int childCenter = (childView.getLeft() + childView.getRight()) / 2;         //获取子view中心线的位置
                    Log.i("test", "first Item center:" + childCenter);
                    if (displayWidth == 0) {
                        displayWidth = ((WindowManager)MainActivity.this.getSystemService(WINDOW_SERVICE))
                                .getDefaultDisplay().getWidth();
                    }
                    int displayCenter = displayWidth / 2;
                    Log.i("test", "display center:" + displayCenter);
                    //滚动矫正不需要再次启动调整
                    refresh = false;
                    if (childCenter >= 0) {
                        Log.i("test", "scrollBy:" + (displayCenter - childCenter));

                        //第一可见条目超过一半还未退出
                        //使firstVisibleItem位于中间
                        scrollWithAnimation(childView, childCenter - displayCenter, 0);
                    } else {
                        Log.i("test", "scrollBy:" + (displayCenter - childCenter - childWidth));

                        //使secondVisibleItem位于中间
                        // 此处因为计算过，不可能存在lastVisible显示超过一半而firstVisible还没退出的情况
                        scrollWithAnimation(layoutManager.findViewByPosition(first + 1)
                                , childCenter + childWidth - displayCenter, 0);
                    }
                } else if (newState == SCROLL_STATE_IDLE) {
                    //恢复滚动后调整启动状态
                    refresh = true;
                } else if (newState == SCROLL_STATE_DRAGGING) {
                    View zoomView = layoutManager.findViewByPosition(((LinearLayoutManager) layoutManager)
                            .findFirstVisibleItemPosition() + 1);
                    AnimatorSet set = new AnimatorSet();
                    ObjectAnimator animator = ObjectAnimator.ofFloat(zoomView, "scaleX",1.1f,1.0f);
                    ObjectAnimator animator1 = ObjectAnimator.ofFloat(zoomView, "scaleY",1.1f,1.0f);
                    ObjectAnimator animator2 = ObjectAnimator.ofFloat(zoomView, "translationY",80f,0f);
                    set.play(animator).with(animator1).with(animator2);
                    int duration = 200;
                    set.setDuration(duration);
                    set.start();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        rv.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.Adapter<MyViewHolder> adapter = new MyAdapter(this, dataList);
        rv.setAdapter(adapter);
        final ViewTreeObserver viewTreeObserver = rv.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                rv.scrollToPosition(1000 - 1000 % dataList.size());
            }
        });
    }

    //带动画滑动
    private void scrollWithAnimation(View view, int x, int y) {
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "scaleX",1f,1.08f,1.1f);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "scaleY",1f,1.08f,1.1f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "translationY",0f,100f);
        set.play(animator).with(animator1).with(animator2);
        int duration = (((Math.abs(x) / displayWidth) + 1) * 300);
        set.setDuration(duration);
        set.start();
        rv.smoothScrollBy(x, y);                     //控制滚动值，x>0向右，y>0向下

    }

    public void viewPager(View view) {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", (Serializable) dataList);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void cardStack(View view) {
        Intent intent = new Intent(MainActivity.this, Main3Activity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", (Serializable) dataList);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void gridView(View view) {
        Intent intent = new Intent(MainActivity.this, Main4Activity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", (Serializable) dataList);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void waterFall(View view) {
        Intent intent = new Intent(MainActivity.this, Main5Activity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", (Serializable) dataList);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
