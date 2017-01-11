package com.meishipintu.cardviewdemo.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.meishipintu.cardviewdemo.activity.MainActivity;
import com.meishipintu.cardviewdemo.adapter.MyAdapter;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by Administrator on 2017/1/10.
 */

public class MyCardStackRecyclerView extends RecyclerView {

    private int dx,dy,touchDownX, touchDownY,dispalyWidth, displayHeight;
    private View topView, nextView;
    private WindowManager windowManager;
    private MyLayoutManager layoutManager;

    public MyCardStackRecyclerView(Context context) {
        super(context);
    }

    public MyCardStackRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCardStackRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        super.setLayoutManager(layout);
        this.layoutManager = (MyLayoutManager) layout;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        if (getChildCount() == 0) {
            return super.onTouchEvent(e);
        }

        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                topView = layoutManager.getTopView();
                nextView = layoutManager.getNextView();
                touchDownX = (int) e.getX();
                touchDownY = (int) e.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                dx = (int) (e.getX()-touchDownX);
                dy = (int) (e.getY()-touchDownY);
                topView.setTranslationX(dx);
                topView.setTranslationY(dy);

                if(nextView!=null){
                    int width = topView.getWidth() / 2;
                    float fraction = calcuFraction(Math.abs(dx),Math.abs(dy),width);
                    nextView.setScaleX(fraction);
                    nextView.setScaleY(fraction);
                }
                break;

            case MotionEvent.ACTION_UP:
                if(isOut(topView)){
                    removeTop();
                }else {
                    BackAnimation(topView);
                }
                break;
        }

        return super.onTouchEvent(e);
    }

    //判断当前view是否越边界
    private boolean isOut(View topView) {
        if (windowManager == null) {
            windowManager = (WindowManager)getContext().getSystemService(WINDOW_SERVICE);
            dispalyWidth = windowManager.getDefaultDisplay().getWidth();
            displayHeight = windowManager.getDefaultDisplay().getHeight();
        }
        if (Math.abs(dx) > dispalyWidth * 3 / 8 || Math.abs(dy) > displayHeight * 3 / 8) {
            return true;
        }
        return false;
    }

    //返回缩放比例因素，返回（0.8，1.0）由拖动相对原位置的distance决定
    private float calcuFraction(int abs, int abs1, int width) {
        float distance = (float) Math.sqrt(Math.pow(abs, 2) + Math.pow(abs1, 2));
        return Math.min(1, 0.8f + distance / (5 * width));
    }

    public void removeTop() {
        //从recyclerView的view队列中移除
        removeView(topView);
        //从自定义的cacheView的队列中移除，因为此队列中的view保存了状态，不能重复使用，需要重新生成
        layoutManager.removeCacheView(topView);
        int currentItem = layoutManager.getCurrentItem();
        layoutManager.setCurrentItem(currentItem - 1);
        MyAdapter adapter = (MyAdapter) getAdapter();
        adapter.setItemCount(adapter.getmCount() - 1);
        adapter.notifyDataSetChanged();
    }

    private void BackAnimation(View view){
        shakeAnimation(view, dx, dy);
    }

    public void shakeAnimation(View view, float curX, float curY) {
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "translationX", curX, 0);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "translationY", curY, 0);
        if (nextView.getScaleX() > 0.8f) {
            ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(nextView, "scaleX", nextView.getScaleX(), 0.8f);
            ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(nextView, "scaleX", nextView.getScaleY(), 0.8f);
            set.play(animator1).with(animator2).with(animatorScaleX).with(animatorScaleY);
        } else {
            set.play(animator1).with(animator2);
        }
        set.setInterpolator(new OvershootInterpolator());
        set.setDuration(200);
        set.start();
    }
}
