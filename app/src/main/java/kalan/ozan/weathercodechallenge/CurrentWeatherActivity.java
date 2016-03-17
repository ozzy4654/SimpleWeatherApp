package kalan.ozan.weathercodechallenge;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.*;

import butterknife.*;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import kalan.ozan.weathercodechallenge.adapters.FiveDayAdapter;
import kalan.ozan.weathercodechallenge.api.model.MyData;
import kalan.ozan.weathercodechallenge.fragments.DetailedConditionsFragment;

public class CurrentWeatherActivity extends AppCompatActivity {

    private static final String MY_PREFS = "MetricOrNo";

    private RecyclerView.Adapter       mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences.Editor   editor;
    private DetailedConditionsFragment deFrag;

    @Bind(R.id.my_recycler_view) RecyclerView m5DayForecast;
    @Bind(R.id.fragFrame)        FrameLayout  mCurrentConditions;
    @Bind(R.id.backgroun) LinearLayout  mBack;

    @Bind(R.id.celsiusToggle) Switch mCelsius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.current_weather);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Search Results");


        Gson gson = new Gson();
        final MyData mdata = gson.fromJson(getIntent().getStringExtra("DATA"), MyData.class);

        Bundle bundle = new Bundle();
        bundle.putString("DATA", getIntent().getStringExtra("DATA"));
        bundle.putInt("Day", 0);

        deFrag = DetailedConditionsFragment.newInstance();
        deFrag.setArguments(bundle);

        getFragmentManager().beginTransaction()
                .add(R.id.fragFrame, deFrag)
                .addToBackStack("current_conditions")
                .commit();




        SharedPreferences mPrefs = getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        Boolean restoredBool = mPrefs.getBoolean("Celsius", false);
        if (restoredBool) {
            mCelsius.setChecked(true);
        } else {
            mCelsius.setChecked(false);
        }

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        m5DayForecast.setLayoutManager(layoutManager);
        mAdapter = new FiveDayAdapter(mdata, this);
        m5DayForecast.setAdapter(mAdapter);

    }

//    public static int loadImage (int forecastCode){
//
////        mBack.setBackgroundResource(R.drawable.cloudy_converted);
//        return forecastCode;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            super.onBackPressed();
        }
       return true;
    }

    @OnClick(R.id.celsiusToggle)
    public void upDatePrefs() {
        editor = getSharedPreferences(MY_PREFS, MODE_PRIVATE).edit();
        if (mCelsius.isChecked()) {
            editor.putBoolean("Celsius", true).commit();
            metricChange();
        } else {
            editor.putBoolean("Celsius", false).commit();
            metricChange();
        }
    }

    public void metricChange() {
        Intent intent = new Intent(this, CurrentWeatherActivity.class);
        intent.putExtra("DATA", getIntent().getStringExtra("DATA"));
        this.finish();
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
