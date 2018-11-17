package com.dawadoz.dawadoztask.webservice;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Webservices
{
	@GET("daily")
	@Headers({ "Content-Type: application/json", "Accept: application/json" })
	Single<Response> getWeather(@Query("q") String city, @Query("cnt") String count, @Query("units") String units, @Query("appid") String appId);
}
