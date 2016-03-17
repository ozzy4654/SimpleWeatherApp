package kalan.ozan.weathercodechallenge.api;

import kalan.ozan.weathercodechallenge.api.model.*;
import retrofit.Callback;
import retrofit.http.*;

public interface WeatherApi {

    @GET("/weather.ashx?key=754386e0879e0d222dac1866a93d5&tp=24&num_of_days=5&format=json")
    void getData(@Query("q") String location,Callback<Climate> callback);

}
