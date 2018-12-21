package com.bawei.lenovo.pengbo20181221.prensenter;

import com.bawei.lenovo.pengbo20181221.imodel.IModelImp;
import com.bawei.lenovo.pengbo20181221.iview.IView;
import com.bawei.lenovo.pengbo20181221.until.MyCallBack;

import java.util.HashMap;

public class IPrensenterImp implements IPresenter {
        IView iView;
        IModelImp iModelImp;

    public IPrensenterImp(IView iView) {
        this.iView = iView;
        iModelImp=new IModelImp();
    }

    @Override
    public void startRequest(HashMap<String,String> map, String path, Class clazz) {
        iModelImp.requestData(map, path, clazz, new MyCallBack() {
            @Override
            public void getData(Object o) {
                    iView.setData(o);
            }
        });
    }
        //解绑
    public void ondet(){
        if (iModelImp!=null){
            iModelImp=null;
        }
        if (iView!=null){
            iView=null;
        }
    }
}
