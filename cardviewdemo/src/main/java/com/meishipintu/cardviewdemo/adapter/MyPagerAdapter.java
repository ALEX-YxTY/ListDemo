package com.meishipintu.cardviewdemo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Toast;

import com.meishipintu.cardviewdemo.bean.Data;
import com.meishipintu.cardviewdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 */

public class MyPagerAdapter extends PagerAdapter {

    private List<View> convertViewList;

    private Context context;
    private List<Data> dataList;

    public MyPagerAdapter(Context context, List<Data> dataList) {
        this.context = context;
        this.dataList = dataList;
        convertViewList = new ArrayList<>();
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        final Data data = dataList.get(position % dataList.size());
        View view;
        MyViewHolder holder;
        if (convertViewList.size() != 0) {
            view = convertViewList.remove(0);
        } else {
            view = View.inflate(context, R.layout.item_getmi_task_new, null);
            holder = new MyViewHolder(view);
            view.setTag(holder);
        }
        holder = (MyViewHolder) view.getTag();
        holder.tv.setText(data.getTitle());
        holder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, data.getClickShow(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.iv.setImageResource(data.getPicRes());
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。  
        ViewParent vp = view.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(view);
        }
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        convertViewList.add((View) object);
    }

    @Override
    public int getCount() {
        if (dataList.size() == 1 || dataList.size() == 0) {
            return 1;
        }
        return 200 ;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


}
