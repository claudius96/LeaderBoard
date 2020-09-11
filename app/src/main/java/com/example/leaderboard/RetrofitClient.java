package com.example.leaderboard;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://forms.gle/";
    private static RetrofitClient mInstance;
    private Retrofit mRetrofit;
    private RetrofitClient(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized RetrofitClient getInstance(){
        if(mInstance == null){
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }
    public SubmitApi getApi(){
        return mRetrofit.create(SubmitApi.class);

    }
}
