package com.anselmo.isscodingchallenge.api;

import com.anselmo.isscodingchallenge.models.ISSResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Anselmo on 12/2/2017.
 */

public interface ISSService {
    @GET("iss-pass.json")
    Call<ISSResponse> getPasses(@Query("lat") double lat, @Query("lon") double lon);
}

