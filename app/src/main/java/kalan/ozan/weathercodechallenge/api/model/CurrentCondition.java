package kalan.ozan.weathercodechallenge.api.model;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CurrentCondition {

    @SerializedName("cloudcover")
    public String cloudCover;

    public String precipMM;
    public String humidity;
    public String pressure;
    public String visibility;

    @SerializedName("observation_time")
    public String timeOfSearch;

    @SerializedName("FeelsLikeC")
    public String feelsLikeC;
    @SerializedName("FeelsLikeF")
    public String feelsLikeF;
    @SerializedName("temp_C")
    public String tempC;
    @SerializedName("temp_F")
    public String tempF;

    public String weatherCode;
    public String windspeedKmph;
    public String windspeedMiles;

    @SerializedName("winddirDegree")
    public String windDirDegree;
    @SerializedName("winddir16Point")
    public String windDir16Point;

    @SerializedName("weatherDesc")
    public List<WeatherDesc>    desc;
    @SerializedName("weatherIconUrl")
    public List<WeatherIconUrl> icon;

    @Override
    public String toString(){

        ArrayList<String> mCurrentDetails = new ArrayList<>();
        mCurrentDetails.add(cloudCover);
        mCurrentDetails.add(precipMM);
        mCurrentDetails.add(feelsLikeC);
        mCurrentDetails.add(feelsLikeF);
        mCurrentDetails.add(tempC);
        mCurrentDetails.add(tempF);
        mCurrentDetails.add(weatherCode);
        mCurrentDetails.add(humidity);
        mCurrentDetails.add(pressure);
        mCurrentDetails.add(visibility);
        mCurrentDetails.add(timeOfSearch);
        mCurrentDetails.add(windspeedKmph);
        mCurrentDetails.add(windspeedMiles);
        mCurrentDetails.add(windDir16Point);
        mCurrentDetails.add(windDirDegree);
        mCurrentDetails.add(desc.get(0).getWeatherDesc());
        mCurrentDetails.add(icon.get(0).getIconURL());

        return mCurrentDetails.toString();
    }


    public List<WeatherDesc> getDesc() {
        return desc;
    }

    public List<WeatherIconUrl> getUrl() {
        return icon;
    }

    public String getHumidity(){
        return humidity;
    }

    public String getPressure(){
        return pressure;
    }
    public String getVisibility(){
        return visibility;
    }

    public String getTimeOfSearch() {
        return timeOfSearch;
    }

    public String getWindspeedKmph() {
        return windspeedKmph;
    }

    public String getWindspeedMiles() {
        return windspeedMiles;
    }

    public String getWindDirDegree() {
        return windDirDegree;
    }

    public String getWindDir16Point() {
        return windDir16Point;
    }

    public List<WeatherIconUrl> getIcon() {
        return icon;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public String getFeelsLikeC() {
        return feelsLikeC;
    }

    public String getFeelsLikeF() {
        return feelsLikeF;
    }

    public String getPrecipMM() {
        return precipMM;
    }

    public String getTempC() {
        return tempC;
    }

    public String getTempF() {
        return tempF;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

}




