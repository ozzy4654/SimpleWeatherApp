package kalan.ozan.weathercodechallenge.fragments;


import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import kalan.ozan.weathercodechallenge.R;
import kalan.ozan.weathercodechallenge.api.model.*;

public class DetailedConditionsFragment extends Fragment {

    private static final String MY_PREFS = "MetricOrNo";

    @Bind(R.id.location)    TextView  mLocation;
    @Bind(R.id.date)        TextView  mDate;
    @Bind(R.id.weatherdesc) TextView  mDescription;
    @Bind(R.id.icon)        ImageView mIcon;
    @Bind(R.id.currentTemp) TextView  mCurrentTemp;
    @Bind(R.id.feelsLike)   TextView  mFeelsLike;

    @Bind(R.id.highTemp)         TextView     mHTemp;
    @Bind(R.id.lowTemp)          TextView     mLTemp;
    @Bind(R.id.humidity)         TextView     mHum;
    @Bind(R.id.precip)           TextView     mPrecip;
    @Bind(R.id.visbility)        TextView     mVis;
    @Bind(R.id.currentTempTitle) TextView     mCTTitle;
    @Bind(R.id.feelsLikeTitle)   TextView     mFLTitle;
    @Bind(R.id.background)       LinearLayout mBack;

    Weather           mWeather;
    CurrentCondition  mCurrent;
    SharedPreferences mPrefs;

    public DetailedConditionsFragment() {

    }

    public static DetailedConditionsFragment newInstance() {
        return new DetailedConditionsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Gson gson = new Gson();
        final MyData mdata = gson.fromJson(getArguments().getString("DATA"), MyData.class);
        final int day = getArguments().getInt("Day");

        mCurrent = mdata.getmCurrentCondition().get(0);
        mWeather = mdata.getmWeather().get(day);
        View view = inflater.inflate(R.layout.weather_detailed_item, container, false);
        ButterKnife.bind(this, view);


        // need to do the date thing again
        mDate.setText(mWeather.getDate());

        //this is for current Conditions
        mLocation.setText(mdata.getmRequest().get(0).getQuery());

        mPrefs = getActivity().getSharedPreferences(MY_PREFS, getActivity().MODE_PRIVATE);
        Boolean restoredBool = mPrefs.getBoolean("Celsius", false);
        if (restoredBool) {
            mFeelsLike.setText(mCurrent.feelsLikeC);
            mHTemp.setText(mWeather.getMaxTempC());
            mLTemp.setText(mWeather.getMinTempC());
            mCurrentTemp.setText(mCurrent.getTempC());
        } else {
            mFeelsLike.setText(mCurrent.feelsLikeF);
            mHTemp.setText(mWeather.getMaxTempF());
            mLTemp.setText(mWeather.getMinTempF());
            mCurrentTemp.setText(mCurrent.getTempF());
        }

        if (day == 0) {
            Picasso.with(getActivity()).
                    load(mCurrent
                                 .getIcon().get(0)
                                 .getIconURL())
                    .into(mIcon);

            mPrecip.setText(String.format("%s%%", mWeather
                    .getHourly().get(0)
                    .chanceOfRain));

            mDescription.setText(mCurrent.desc.get(0).getWeatherDesc());
            mVis.setText(String.format("%sKm", mCurrent.getVisibility()));
            mHum.setText(String.format("%s%%", mCurrent.getHumidity()));

            imageChange(mdata.getmCurrentCondition().get(0).getWeatherCode());

        } else {
            imageChange(mWeather.getHourly().get(0).getWeatherCode());

            mCTTitle.setVisibility(View.INVISIBLE);
            mFLTitle.setVisibility(View.INVISIBLE);
            mCurrentTemp.setVisibility(View.INVISIBLE);
            mFeelsLike.setVisibility(View.INVISIBLE);

            mVis.setText(String.format("%s miles", mWeather
                    .getHourly().get(0)
                    .getVisiblityMile()));

            mHum.setText(String.format("%s%%", mWeather
                    .getHourly().get(0)
                    .getHumidity()));

            mPrecip.setText(String.format("%s%%", mWeather
                    .getHourly().get(0)
                    .chanceOfRain));

            mDescription.setText(mWeather.getHourly().get(0)
                                         .getDesc().get(0)
                                         .getWeatherDesc());

            Picasso.with(getActivity()).
                    load(mWeather
                                 .getHourly().get(0)
                                 .getIcon().get(0)
                                 .getIconURL())
                    .into(mIcon);
        }
        return view;
    }

    public final ArrayList<String> makelist(int list) {
        return new ArrayList<>(Arrays.asList(getString(list)));
    }

    public void imageChange(String forecastCode) {


        if (makelist(R.array.rain_codes).contains(forecastCode)) {
            mBack.setBackgroundResource(R.drawable.raing);
        } else if (makelist(R.array.thunder_codes).contains(forecastCode)) {
            mBack.setBackgroundResource(R.drawable.thunder);
        } else if (makelist(R.array.cloudy_codes).contains(forecastCode)) {
            mBack.setBackgroundResource(R.drawable.cloudy_converted);
        } else if (makelist(R.array.fog_codes).contains(forecastCode)) {
            mBack.setBackgroundResource(R.drawable.fog);
        } else if (makelist(R.array.freezing_rain_codes).contains(forecastCode)) {
            mBack.setBackgroundResource(R.drawable.freezing_rain);
        } else if (makelist(R.array.snow_codes).contains(forecastCode)) {
            mBack.setBackgroundResource(R.drawable.snow);
        } else if (getString(R.string.sunny_code).equals(forecastCode)) {
            mBack.setBackgroundResource(R.drawable.sunny);
        } else if (getString(R.string.partly_cloudy_code).equals(forecastCode)) {
            mBack.setBackgroundResource(R.drawable.partly_cloudy);
        } else if (getString(R.string.extremee_code).equals(forecastCode)) {
            mBack.setBackgroundResource(R.drawable.extreme);
        }

    }
}
