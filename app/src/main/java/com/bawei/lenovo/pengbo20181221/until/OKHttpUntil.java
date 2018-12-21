package com.bawei.lenovo.pengbo20181221.until;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OKHttpUntil {
    /**
     * 1.单例
     */
    private static OKHttpUntil instance;
    private final OkHttpClient client;


    public static OKHttpUntil getInstance(){
        if (instance==null){
            instance=new OKHttpUntil();
        }
        return instance;
    }
    private OKHttpUntil(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("TAG", message);
            }
        });
        //定义Client
        client = new OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

    }

    /**
     * 2.定义接口
     */
    public interface CallBack{
        void fail(Exception e);
        void success(Object o);
    }
    /**
     * 3.post异步
     */
    //handler
    private Handler handler=new Handler(Looper.myLooper());
    public void postEnque(Map<String,String> map, String path, final Class clazz, final CallBack callBack){
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String,String> entry:map.entrySet()) {
            //添加数据
                builder.add(entry.getKey(),entry.getValue());
        }
        RequestBody body = builder.build();
        //建立请求
        Request request = new Request.Builder().post(body).url(path).build();

           Call call= client.newCall(request);
           call.enqueue(new Callback() {
               @Override
               public void onFailure(Call call, final IOException e) {
                   handler.post(new Runnable() {
                       @Override
                       public void run() {
                           callBack.fail(e);
                       }
                   });
               }

               @Override
               public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String string = response.body().string();
                        //gson解析
                        Gson gson = new Gson();
                        final Object o = gson.fromJson(string, clazz);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callBack.success(o);
                            }
                        });
                    }catch (Exception e){
                        e.printStackTrace();
                    }
               }
           });
    }
}
