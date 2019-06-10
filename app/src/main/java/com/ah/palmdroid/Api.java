package com.ah.palmdroid;

import com.ah.palmdroid.API.CenterRandom;
import com.ah.palmdroid.API.MinMax;
import com.ah.palmdroid.API.NilaiW;
import com.ah.palmdroid.API.ThresholdSpread;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.*;

public interface Api {

    String BASE_URL ="http://alelahosting.000webhostapp.com/api-palm/web/";
    String BASE_URL2 ="http://alelahosting.000webhostapp.com/api-palm/web/";
    String BASE_URL3 ="http://alelahosting.000webhostapp.com/api-palm/web/";
    String BASE_URL4 ="http://alelahosting.000webhostapp.com/api-palm/web/";

    @GET("index.php?r=nilai-w/get-nilai-w" )
    Call<List<NilaiW>> getNilaiW();

    @GET("index.php?r=center-random/get-center-random" )
    Call<List<CenterRandom>> getCenterRandom();

    @GET("index.php?r=min-max/get-min-max" )
    Call<List<MinMax>> getMinMax();


    @GET("index.php?r=threshold-spread/get-threshold-spread" )
    Call<List<ThresholdSpread>> getThresholdSpread();




}
