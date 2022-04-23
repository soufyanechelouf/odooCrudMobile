package com.example.odooapp.Controller;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class VolleySingleton {

    private static com.example.odooapp.Controller.VolleySingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    public VolleySingleton(Context context) {
        this.mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized com.example.odooapp.Controller.VolleySingleton getInstance(Context context){
        if(mInstance == null){
            mInstance = new com.example.odooapp.Controller.VolleySingleton(context);
        }
        return  mInstance;
    }

    public  RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return  mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }

}
