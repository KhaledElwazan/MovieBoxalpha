package com.example.moviebox_alpha.retrofit;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {



    @GET("popular")
    Call<MyJSONResponse> getPopular(@Query("api_key") String apikey);


    @GET("top_rated")
    Call<MyJSONResponse> getTopRated(@Query("api_key") String apikey);

}
