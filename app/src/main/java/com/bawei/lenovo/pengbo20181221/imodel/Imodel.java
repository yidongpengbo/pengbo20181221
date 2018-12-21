package com.bawei.lenovo.pengbo20181221.imodel;

import com.bawei.lenovo.pengbo20181221.until.MyCallBack;

import java.util.HashMap;

public interface Imodel {
    void requestData(HashMap<String,String> map, String path, Class clazz, MyCallBack myCallBack);
}
