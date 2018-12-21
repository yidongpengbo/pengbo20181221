package com.bawei.lenovo.pengbo20181221;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.qrcode.encoder.QRCode;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class ZXingActivity extends AppCompatActivity implements QRCodeView.Delegate {
    private ZXingView mZxing;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);
        initView();

    }
    //获取资源ID
    private void initView() {
        mZxing = (ZXingView) findViewById(R.id.Zxing);
        mZxing.setDelegate(this);
    }
    //获焦
    @Override
    protected void onResume() {
        super.onResume();
        //开始扫描
        mZxing.startCamera();
        mZxing.startSpotAndShowRect();
    }
    //停止
    @Override
    protected void onStop() {
        super.onStop();
        //停止扫描
        mZxing.stopCamera();

    }
    //销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mZxing.onDestroy();
        Toast.makeText(ZXingActivity.this,"二维码",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        if (result!=null){
            Toast.makeText(ZXingActivity.this,result,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }
}
