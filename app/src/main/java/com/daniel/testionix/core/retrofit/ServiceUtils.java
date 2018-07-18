package com.daniel.testionix.core.retrofit;

import com.daniel.testionix.core.retrofit.methods.ApiMethods;

public class ServiceUtils {

    private static String baseURL = "http://192.168.1.75:8089/test/";

    private ServiceUtils() {
    }

    public static ApiMethods getAPIService() {

        return ServiceCore.getClient(baseURL).create(ApiMethods.class);

    }

}
