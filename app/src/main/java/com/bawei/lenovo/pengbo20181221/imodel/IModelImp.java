package com.bawei.lenovo.pengbo20181221.imodel;

import com.bawei.lenovo.pengbo20181221.bean.MainBean;
import com.bawei.lenovo.pengbo20181221.bean.OneBean;
import com.bawei.lenovo.pengbo20181221.bean.TwoBean;
import com.bawei.lenovo.pengbo20181221.until.MyCallBack;
import com.bawei.lenovo.pengbo20181221.until.OKHttpUntil;

import java.util.HashMap;

public class IModelImp implements Imodel {
    MyCallBack myCallBack;
    @Override
    public void requestData(HashMap<String,String>map, String path, Class clazz, final MyCallBack myCallBack) {
        this.myCallBack=myCallBack;
        OKHttpUntil.getInstance().postEnque(map, path, clazz, new OKHttpUntil.CallBack() {
            @Override
            public void fail(Exception e) {

            }

            @Override
            public void success(Object o) {
                //判断获取的数据类型
                    if (o instanceof MainBean){
                        MainBean mainBean=(MainBean)o;
                        myCallBack.getData(mainBean);
                    }else if (o instanceof OneBean){
                        OneBean oneBean=(OneBean)o;
                        myCallBack.getData(oneBean);
                    }else if (o instanceof TwoBean){
                        TwoBean twoBean=(TwoBean)o;
                        myCallBack.getData(twoBean);
                    }
            }
        });
    }
}
