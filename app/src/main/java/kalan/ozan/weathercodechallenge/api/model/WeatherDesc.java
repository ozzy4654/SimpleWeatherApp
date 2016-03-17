package kalan.ozan.weathercodechallenge.api.model;

import com.google.gson.annotations.SerializedName;


public class WeatherDesc {
        @SerializedName("value")
        public String weatherDesc;

    public String getWeatherDesc() {
        return weatherDesc;
    }
}
