package com.bawei.lenovo.pengbo20181221.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.lenovo.pengbo20181221.R;
import com.bawei.lenovo.pengbo20181221.bean.OneBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyOneAdapter extends RecyclerView.Adapter<RecyOneAdapter.ViewHolder> {
        private List<OneBean.DataBean> mjihe;
        private Context context;

    public RecyOneAdapter(Context context) {
        this.context = context;
        mjihe=new ArrayList<>();
    }

    public void setMjihe(List<OneBean.DataBean> mjihe) {
        this.mjihe = mjihe;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.oneadapter,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(context).load(mjihe.get(i).getIcon()).into(viewHolder.imageView);
        viewHolder.textView.setText(mjihe.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mjihe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.oneImage);
            textView=itemView.findViewById(R.id.oneText);
        }
    }
}
