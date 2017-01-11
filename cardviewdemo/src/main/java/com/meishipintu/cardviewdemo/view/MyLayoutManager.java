package com.meishipintu.cardviewdemo.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/1/10.
 */

public class MyLayoutManager extends RecyclerView.LayoutManager {

    private int currentItem;
    private int realSize;
    //保存缓存View，不需要重新绑定
    private List<View> cacheView;
    private View topView, nextView;

    public MyLayoutManager(int realSize, int size) {
        this.realSize = realSize;
        this.currentItem = size-1;
        cacheView = new ArrayList<>();
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.i("test", "onLayoutChildren");
        removeAllViews();
        Random random = new Random();
        int parentWidth = getWidth();

        //每次只载入5个view，test
        for (int i = 5; i > 0; i--) {
            if (currentItem < 4) {
                currentItem = 99 - 99 % realSize;
            }
            int realPosition = (currentItem+1 - i) % realSize;
            View child = containsView(realPosition);
            if (child == null) {
                child = recycler.getViewForPosition(currentItem+1 - i);
                child.setRotation((float) (Math.pow(-1, random.nextInt()) * random.nextInt(15)));
                child.setTranslationX((float) (Math.pow(-1, random.nextInt()) * random.nextInt(30)));
                child.setTranslationY((float) (Math.pow(-1, random.nextInt()) * random.nextInt(60)));
                child.setTag(realPosition);
                //将view加入自定义缓存队列，此队列中包含了view的参数信息
                cacheView.add(child);
            }
            //addView会去重,所以不能取view队列中的view作为topView和nextView
            addView(child);
            if (i == 2) {
                nextView = child;
            } else if (i == 1) {
                topView = child;
            }
            measureChildWithMargins(child, 0, 0);
            int width = getDecoratedMeasuredWidth(child);
            int height = getDecoratedMeasuredHeight(child);
            layoutDecorated(child, (parentWidth - width) / 2, 0, (parentWidth + width) / 2, height);


            if (i > 1) {
                child.setScaleX(0.8f);
                child.setScaleY(0.8f);
            } else {
                //还原，因为缓存中的view可能已经缩小了
                child.setScaleX(1.0f);
                child.setScaleY(1.0f);
            }
        }
    }

    //判断当前position的view是否已经被包含在cacheList中
    private View containsView(int realPosition) {
        for (View view : cacheView) {
            if ((int)view.getTag() == realPosition) {
                return view;
            }
        }
        return null;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        RecyclerView.LayoutParams layoutParams =
                new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
        return layoutParams;
    }

    public int getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(int newCurrent) {
        this.currentItem = newCurrent;
    }

    public View getTopView() {
        return this.topView;
    }

    public View getNextView() {
        return this.nextView;
    }

    public void removeCacheView(View view) {
        cacheView.remove(view);
    }
}
