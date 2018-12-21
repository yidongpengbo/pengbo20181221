package com.bawei.lenovo.pengbo20181221;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bawei.lenovo.pengbo20181221.adapter.ViewPagerAdapter;
import com.bawei.lenovo.pengbo20181221.bean.MainBean;
import com.bawei.lenovo.pengbo20181221.iview.IView;
import com.bawei.lenovo.pengbo20181221.prensenter.IPrensenterImp;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements IView {

    private ViewPager mViewpager;
    private ViewPagerAdapter adapter;
    private IPrensenterImp iPrensenterImp;
    private Button button;
    //handler
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //得到当前的图片
            int item = mViewpager.getCurrentItem();
                mViewpager.setCurrentItem(item+1);
                Log.i("TAG","item="+item+1);
            handler.sendEmptyMessageDelayed(0,1000);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initViewPager();
    }
        //获取数据
    private void initViewPager() {
          String path="http://www.zhaoapi.cn/ad/getAd";
        HashMap<String, String> map = new HashMap<>();
        iPrensenterImp.startRequest(map,path,MainBean.class);

        //点击事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                Intent intent = new Intent(MainActivity.this, ShopActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 获取资源ID
     */
    private void initView() {
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
       adapter=new ViewPagerAdapter(this);
       mViewpager.setAdapter(adapter);
        iPrensenterImp=new IPrensenterImp(this);
        button=(Button)findViewById(R.id.Button);
    }
    //得到数据
    @Override
    public void setData(Object o) {
        if (o instanceof MainBean){
            MainBean mainBean=(MainBean)o;
            adapter.setMjihe(mainBean.getData());
            //切换
            int count = adapter.getCount();
            Log.i("TAG","count"+count);
            mViewpager.setCurrentItem(count%count);
            handler.sendEmptyMessageDelayed(0,2000);

        }
    }

    @Override   //解绑
    protected void onDestroy() {
        super.onDestroy();
        iPrensenterImp.ondet();
    }
}
