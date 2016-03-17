package kalan.ozan.weathercodechallenge.api.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Weather {

    List<Astronomy> astronomies;
    public String date;
    public List<Hourly> hourly;

    @SerializedName("maxtempC")
    public String maxTempC;
    @SerializedName("mintempC")
    public String minTempC;

    @SerializedName("maxtempF")
    public String maxTempF;
    @SerializedName("mintempF")
    public String minTempF;

    public List<Astronomy> getAstronomies() {
        return astronomies;
    }

    public String getDate() {
        return date;
    }

    public List<Hourly> getHourly() {
        return hourly;
    }

    public String getMaxTempC() {
        return maxTempC;
    }

    public String getMinTempC() {
        return minTempC;
    }

    public String getMaxTempF() {
        return maxTempF;
    }

    public String getMinTempF() {
        return minTempF;
    }

    public String getUvIndex() {
        return uvIndex;
    }

    public String uvIndex;


}
