package com.daniel.testionix.core.retrofit.methods;

import com.daniel.testionix.core.models.LoginObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiMethods {

    @GET("search")
    Call<LoginObject> getUser(@Query("rut") String rut);

}
