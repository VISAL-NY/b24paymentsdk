package com.bill24.onlinepaymentsdk.core;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance=null;
    private ApiClient apiClient;

    private RetrofitClient(String BaseURl){
        // Create an OkHttpClient with a logging interceptor
        OkHttpClient.Builder httpClient=new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);

        Retrofit retrofit=new Retrofit.Builder().baseUrl(BaseURl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build()).build();
        apiClient=retrofit.create(ApiClient.class);
    }

    public static RetrofitClient getInstance(String BaseUrl){
        if(instance==null){
            instance=new RetrofitClient(BaseUrl);
        }
        return instance;
    }

    public ApiClient getApiClient(){
        return apiClient;
    }

}
