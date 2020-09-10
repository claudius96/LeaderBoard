package com.example.leaderboard;

import android.widget.Button;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SubmitApi {

    @POST("formResponse")
    @FormUrlEncoded
    Call<ResponseBody> submit(
            @Field("Email Address") String email,
            @Field("First Name") String first_name,
            @Field("Last Name") String last_name,
            @Field("Link your project on GitHub") String githubLink

    );
}
