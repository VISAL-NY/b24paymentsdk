package com.example.bill24paymentonline.model;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientApp {
    private static RetrofitClientApp instance=null;
    private ApiClientApp apiClient;

    private RetrofitClientApp(){
        // Create an OkHttpClient with a logging interceptor
        OkHttpClient.Builder httpClient=new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);

        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://merchantapi-demo.bill24.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build()).build();
        apiClient=retrofit.create(ApiClientApp.class);
    }

    public static RetrofitClientApp getInstance(){
        if(instance==null){
            instance=new RetrofitClientApp();
        }
        return instance;
    }

    public ApiClientApp getApiClientApp(){
        return apiClient;
    }
}
