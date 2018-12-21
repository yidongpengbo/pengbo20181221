package com.bawei.lenovo.pengbo20181221;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bawei.lenovo.pengbo20181221.adapter.RecyOneAdapter;
import com.bawei.lenovo.pengbo20181221.adapter.RecyTwoAdapter;
import com.bawei.lenovo.pengbo20181221.bean.OneBean;
import com.bawei.lenovo.pengbo20181221.bean.TwoBean;
import com.bawei.lenovo.pengbo20181221.iview.IView;
import com.bawei.lenovo.pengbo20181221.prensenter.IPrensenterImp;

import java.security.Permission;
import java.util.HashMap;
import java.util.List;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener,IView {
    /**
     * 二维码
     */
    private Button mZxing;
    private EditText mSearch;
    /**
     * 切换
     */
    private Button mQie;
    private RecyclerView mRecyclerOne;
    private RecyclerView mRecyclerTwo;
    private IPrensenterImp iPrensenterImp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        initView();
        initRecyclerOne();
        initRecyclerTwo();
    }
    RecyTwoAdapter twoAdapter;
    //设置底部布局的格式
    private void initRecyclerTwo() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerTwo.setLayoutManager(manager);
        //适配器
        twoAdapter=new RecyTwoAdapter(this);
        mRecyclerTwo.setAdapter(twoAdapter);
        initTwo();
    }
        //根据网址获取底部数据
    private void initTwo() {
        String pathTwo="http://www.zhaoapi.cn/product/getCarts";
        HashMap<String, String> map = new HashMap<>();
        map.put("uid",71+"");
        //执行P层
        iPrensenterImp.startRequest(map,pathTwo,TwoBean.class);
    }

    //设置中部布局的格式
    RecyOneAdapter oneAdapter;
    private void initRecyclerOne() {
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecyclerOne.setLayoutManager(manager);
        oneAdapter=new RecyOneAdapter(this);
        mRecyclerOne.setAdapter(oneAdapter);
        initOne();
    }
        //获取数据
    private void initOne() {
        String pathOne="http://www.zhaoapi.cn/product/getCatagory";
        HashMap<String, String> map = new HashMap<>();
        iPrensenterImp.startRequest(map,pathOne,OneBean.class);
    }

    /**
     * 获取资源ID
     */
    private void initView() {
        mZxing = (Button) findViewById(R.id.Zxing);
        mZxing.setOnClickListener(this);
        mSearch = (EditText) findViewById(R.id.search);
        mQie = (Button) findViewById(R.id.Qie);
        mQie.setOnClickListener(this);
        mRecyclerOne = (RecyclerView) findViewById(R.id.Recycler_one);
        mRecyclerTwo = (RecyclerView) findViewById(R.id.Recycler_two);
        iPrensenterImp=new IPrensenterImp(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
                //二维码扫描
            case R.id.Zxing:
                initZXing();
                break;
                //切换底部
            case R.id.Qie:
                //得到数据
                List<TwoBean.DataBean.LinBean> list = twoAdapter.getMjihe();
                initQie();
                break;
        }
    }
    //切换
    //定义一个标记
    boolean boo=true;
    private void initQie() {
        if (boo){
            //为网格格式
            GridLayoutManager manager = new GridLayoutManager(this, 2);
            mRecyclerTwo.setLayoutManager(manager);
        }else {
            //为linear格式
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mRecyclerTwo.setLayoutManager(manager);
        }
    //改变标记
        boo=!boo;
    }

    //二维码扫描
    private void initZXing() {
        //跳转
        Intent intent = new Intent(ShopActivity.this,ZXingActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==100&&grantResults[0]==PackageManager.PERMISSION_GRANTED) {
            //跳转
            Intent intent = new Intent(ShopActivity.this,ZXingActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void setData(Object o) {
            //判断数据的类型
            if (o instanceof OneBean){
                OneBean oneBean=(OneBean)o;
                oneAdapter.setMjihe( oneBean.getData());
            }else if (o instanceof TwoBean){
                TwoBean twoBean=(TwoBean)o;
                //获取商品集合
                List<TwoBean.DataBean> data = twoBean.getData();
                for (int i = 0; i <data.size() ; i++) {
                        twoAdapter.setMjihe(data.get(i).getList());
                }
            }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPrensenterImp.ondet();
    }
}
