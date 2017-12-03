package com.anselmo.isscodingchallenge.api;

import com.anselmo.isscodingchallenge.models.ISSResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Anselmo on 12/2/2017.
 */

public interface ISSService {
    /**
     * This function return a simple callback retrofit.
     *
     * This is very very basic and I don't like to work in this way, but I don't have much time.
     * A good approaching  would be to use a RxAndroid/RxJava and return an Observable and then
     * could do a lot of things with the observable such as: filter results, transform objects, etc.
     *
     * @param lat - Current latitude
     * @param lon - Current longitude
     * @return A retrofit Callback
     */
    @GET("iss-pass.json")
    Call<ISSResponse> getPasses(@Query("lat") double lat, @Query("lon") double lon);
}

