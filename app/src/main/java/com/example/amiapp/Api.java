package com.example.amiapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Belal on 10/2/2017.
 */

public interface Api {

    String BASE_URL = "http://68.66.207.7/api/";

    @GET("ley")
    Call<List<Ley>> getLeyes();
}