package com.bawei.lenovo.pengbo20181221.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.lenovo.pengbo20181221.R;
import com.bawei.lenovo.pengbo20181221.bean.TwoBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyTwoAdapter extends RecyclerView.Adapter<RecyTwoAdapter.ViewHolder> {
        private Context context;
        private List<TwoBean.DataBean.LinBean> mjihe;

    public RecyTwoAdapter(Context context) {
        this.context = context;
        mjihe=new ArrayList<>();
    }

    public void setMjihe(List<TwoBean.DataBean.LinBean> mjihe) {
        this.mjihe = mjihe;
        notifyDataSetChanged();
    }

    public List<TwoBean.DataBean.LinBean> getMjihe() {
        return mjihe;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.twoadapter,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String images = mjihe.get(i).getImages();
        String[] split = images.split("\\|");
        final String image = split[0].replace("https", "http");
        Glide.with(context).load(image).into(viewHolder.imageView);
        viewHolder.textTitle.setText(mjihe.get(i).getTitle());
        viewHolder.textPrice.setText(mjihe.get(i).getBargainPrice()+"");
        //条目点击事件
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context,"url="+image,Toast.LENGTH_LONG).show();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mjihe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textTitle,textPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.twoImage);
            textTitle=itemView.findViewById(R.id.TwoTitle);
            textPrice=itemView.findViewById(R.id.TwoPrice);
        }
    }
}
