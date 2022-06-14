package com.example.android45_app_doc_bao_retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClientlpm {
    static String urlLink = "https://demo5764501.mockable.io/";
    static IService iService;
    Retrofit retrofit = null;

    public static IService getiService(){
        if(iService == null){
            OkHttpClient okHttpClient = new OkHttpClient();
            Retrofit restAdapter = newRetrofitService(urlLink,okHttpClient);
            iService = restAdapter.create(IService.class);
        }
        return iService;
    }

    private static Retrofit newRetrofitService(String urlLink, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(urlLink)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
