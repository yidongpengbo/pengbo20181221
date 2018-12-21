package com.bawei.lenovo.pengbo20181221.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bawei.lenovo.pengbo20181221.bean.MainBean;
import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private List<MainBean.DataBean> mjihe;
    private Context context;

    public ViewPagerAdapter(Context context) {
        this.context = context;
        mjihe=new ArrayList<>();
    }

    public void setMjihe(List<MainBean.DataBean> mjihe) {
        this.mjihe = mjihe;
        notifyDataSetChanged();
    }

    @Override   //长度
    public int getCount() {
        return mjihe.size()>0?4:0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        container.addView(imageView);
        ImageLoader.getInstance().displayImage(mjihe.get(position).getIcon(),imageView);
        return imageView;
    }

    @Override   //清除
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }
}
