package com.meishipintu.cardviewdemo.adapter;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Administrator on 2017/1/4.
 */

public class MyTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 1.0f;
    private static final float MAX_ROTATE = 7.5f;

    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position <= 1.2 && position >= -1.2) {         // (-1,1) 可见状态，设置动画效果
            float scaleFactor = Math.max(MIN_SCALE, 1.2f - Math.abs(position)/2.0f);
            float rotateFactor = Math.min(MAX_ROTATE, 7.5f * Math.abs(position));
            float horzMargin = pageWidth * (1.2f - scaleFactor) / 2;
            if (position > 0) {
                view.setRotationY(rotateFactor);
                view.setTranslationX(horzMargin );
            } else {
                view.setTranslationX(-horzMargin );
                view.setRotationY(-rotateFactor);
            }
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        }
    }
}
