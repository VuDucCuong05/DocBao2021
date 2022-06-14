package com.example.android45_app_doc_bao_retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IService {
    @GET("thumucr")
    Call<List<ThuMucRetrofit>> getThuMucRetrofit();
}
