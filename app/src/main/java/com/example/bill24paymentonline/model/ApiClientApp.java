package com.example.bill24paymentonline.model;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiClientApp {
    @POST("transaction/v2/init")
    Call<ResponseModel> initTranV2(
            @Header("Content-Type") String contentType,
            @Header("token") String token,
            @Body RequestModel requestModel);
}
