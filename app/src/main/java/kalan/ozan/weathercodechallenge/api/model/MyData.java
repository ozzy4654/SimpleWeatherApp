package kalan.ozan.weathercodechallenge.api.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MyData {

    @SerializedName("current_condition")
    public List<CurrentCondition> mCurrentCondition;

    @SerializedName("request")
    public List<Request> mRequest;

    @SerializedName("weather")
    public List<Weather> mWeather;

    public List<SearchError> error;

    @Override
    public String toString() {

        ArrayList<List> mDataDetails = new ArrayList<>();
        mDataDetails.add(mCurrentCondition);
        mDataDetails.add(mRequest);
        mDataDetails.add(mWeather);

        return mDataDetails.toString();
    }

    public List<CurrentCondition> getmCurrentCondition() {
        return mCurrentCondition;
    }

    public List<Request> getmRequest() {
        return mRequest;
    }

    public List<Weather> getmWeather() {
        return mWeather;
    }

}


