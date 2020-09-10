package com.example.leaderboard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiLeaderboard {
    String BASE_URL = "https://gadsapi.herokuapp.com/api/";
    @GET("skilliq")
    Call<List<LeaderBoardData>> getArticle();
}
