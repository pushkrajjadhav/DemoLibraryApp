package com.codeplateau.vidgyor;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

public interface RetroAPI {

    // Global Variable
    public static String pref = "OTACRM1234";

    @FormUrlEncoded
    @POST("/userdetails/login")
    public void url_login(@Field("email") String UEId,
                          @Field("password") String UPwd,
                          Callback<retrofit.client.Response> response);

    @GET("/test/lib_app_multiple.status")
    public void url_getdata(Callback<retrofit.client.Response> response);


}
