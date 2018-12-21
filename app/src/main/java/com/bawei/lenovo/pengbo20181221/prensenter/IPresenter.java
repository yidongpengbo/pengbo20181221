package com.bawei.lenovo.pengbo20181221.prensenter;

import java.util.HashMap;

public interface IPresenter {
    void startRequest(HashMap<String,String>map,String path,Class clazz);
}
