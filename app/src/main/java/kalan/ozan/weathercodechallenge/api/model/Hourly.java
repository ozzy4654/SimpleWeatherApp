package kalan.ozan.weathercodechallenge.api.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Hourly {

    @SerializedName("chanceoffost")
    public String chanceOfFrost;
    @SerializedName("chanceofhightemp")
    public String chanceOfHighTemp;
    @SerializedName("chanceofovercast")
    public String chanceOfOvercast;
    @SerializedName("chanceofremdry")
    public String chanceOfremdry;
    @SerializedName("chanceofwindy")
    public String chanceOfWindy;
    @SerializedName("chanceofrain")
    public String chanceOfRain;
    @SerializedName("chanceofsnow")
    public String chanceOfSnow;
    @SerializedName("chanceofthunder")
    public String chanceOfThunder;
    @SerializedName("chanceofsunshine")
    public String chanceOfSunshine;
    @SerializedName("chanceoffog")
    public String chanceOfFog;


    @SerializedName("DewPointC")
    public String dewPointC;
    @SerializedName("DewPointF")
    public String dewPointF;

    @SerializedName("FeelsLikeC")
    public String feelsLikeC;
    @SerializedName("FeelsLikeF")
    public String feelsLikeF;

    @SerializedName("HeatIndexC")
    public String heatIndexC;
    @SerializedName("HeatIndexF")
    public String heatIndexF;

    @SerializedName("humidity")
    public String humidity;
    @SerializedName("precipMM")
    public String preceipMM;

    @SerializedName("tempC")
    public String tempC;

    @SerializedName("tempF")
    public String tempF;

    @SerializedName("time")
    public String time;

    @SerializedName("visibility")
    public String visiblityMile;

    @SerializedName("WindChillC")
    public String windChillC;

    @SerializedName("WindChillF")
    public String WindChillF;

    @SerializedName("weatherCode")
    public String weatherCode;

    @SerializedName("weatherDesc")
    public List<WeatherDesc> desc;

    @SerializedName("weatherIconUrl")
    public List<WeatherIconUrl> icon;

    @SerializedName("winddirDegree")
    public String windDirDegree;
    @SerializedName("winddir16Point")
    public String windDir16Point;
    @SerializedName("WindGustKmph")
    public String windGustKmph;
    @SerializedName("WindGustMiles")
    public String windGustMiles;

    public String windspeedKmph;
    public String windspeedMiles;

    public String getChanceOfHighTemp() {
        return chanceOfHighTemp;
    }

    public String getChanceOfOvercast() {
        return chanceOfOvercast;
    }

    public String getChanceOfWindy() {
        return chanceOfWindy;
    }

    public String getChanceOfremdry() {
        return chanceOfremdry;
    }

    public String getChanceOfRain() {
        return chanceOfRain;
    }

    public String getChanceOfSnow() {
        return chanceOfSnow;
    }

    public String getChanceOfThunder() {
        return chanceOfThunder;
    }

    public String getChanceOfSunshine() {
        return chanceOfSunshine;
    }

    public String getChanceOfFog() {
        return chanceOfFog;
    }

    public String getDewPointC() {
        return dewPointC;
    }

    public String getDewPointF() {
        return dewPointF;
    }

    public String getFeelsLikeC() {
        return feelsLikeC;
    }

    public String getFeelsLikeF() {
        return feelsLikeF;
    }

    public String getHeatIndexC() {
        return heatIndexC;
    }

    public String getHeatIndexF() {
        return heatIndexF;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPreceipMM() {
        return preceipMM;
    }

    public String getTempC() {
        return tempC;
    }

    public String getTempF() {
        return tempF;
    }

    public String getTime() {
        return time;
    }

    public String getVisiblityMile() {
        return visiblityMile;
    }

    public String getWindChillC() {
        return windChillC;
    }

    public String getWindChillF() {
        return WindChillF;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public List<WeatherDesc> getDesc() {
        return desc;
    }

    public List<WeatherIconUrl> getIcon() {
        return icon;
    }

    public String getWindDirDegree() {
        return windDirDegree;
    }

    public String getWindDir16Point() {
        return windDir16Point;
    }

    public String getWindGustKmph() {
        return windGustKmph;
    }

    public String getWindGustMiles() {
        return windGustMiles;
    }

    public String getWindspeedKmph() {
        return windspeedKmph;
    }

    public String getWindspeedMiles() {
        return windspeedMiles;
    }

    public String getChanceOfFrost() {

        return chanceOfFrost;
    }
}


