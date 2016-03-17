package kalan.ozan.weathercodechallenge.api.model;

import com.google.gson.annotations.SerializedName;

public class WeatherIconUrl {
    @SerializedName("value")
    public String iconURL;

    public String getIconURL() {
        return iconURL;
    }
}
