package kalan.ozan.weathercodechallenge.adapters;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import kalan.ozan.weathercodechallenge.R;
import kalan.ozan.weathercodechallenge.api.model.MyData;
import kalan.ozan.weathercodechallenge.api.model.Weather;
import kalan.ozan.weathercodechallenge.fragments.DetailedConditionsFragment;


public class FiveDayAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>implements View.OnClickListener{

    private static final String MY_PREFS_NAME = "MetricOrNo";
    private Activity      mContext;
    private List<Weather> m5Days;
    private String            myData;
    private SharedPreferences mPrefs;
    private Boolean restoredBool;

    public FiveDayAdapter(MyData mdata, Activity parent) {
        mContext = parent;
        Gson gson = new Gson();
        myData = gson.toJson(mdata);
        m5Days = mdata.mWeather;
    }


    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.weather_summary_item, parent, false);

        return new VH(v);
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date mDate = null;

        try {
            mDate = sdf.parse(m5Days.get(position).getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String mDay = android.text.format.DateFormat.format("EE", mDate).toString();

        ((VH) holder).mData = m5Days.get(position);
        ((VH) holder).mSItem.setOnClickListener((View.OnClickListener) this);
        ((VH) holder).mSItem.setTag(position);
        ((VH) holder).mDate.setText(mDay);
        Picasso.with(mContext).load(m5Days.get(position)
                .getHourly().get(0)
                .getIcon().get(0)
                .getIconURL())
                .into(((VH) holder).mIcon);


        mPrefs =mContext.getSharedPreferences(MY_PREFS_NAME, mContext.MODE_PRIVATE);
        restoredBool = mPrefs.getBoolean("Celsius", false);

        if (restoredBool) {
            ((VH) holder).mHighTemp.setText(String.format("%s/", m5Days.get(position).getMaxTempC()));
            ((VH) holder).mLowTemp.setText(m5Days.get(position).getMinTempC());
        }else {
            ((VH) holder).mHighTemp.setText(String.format("%s/", m5Days.get(position).getMaxTempF()));
            ((VH) holder).mLowTemp.setText(m5Days.get(position).getMinTempF());
        }
    }

    @Override public int getItemCount() {
        return m5Days.size();
    }

    @Override public void onClick(View v) {
        int position = (int) v.getTag();

        Bundle bundle = new Bundle();
        bundle.putString("DATA", myData);
        bundle.putInt("Day", position);

        DetailedConditionsFragment detailedFragment = new DetailedConditionsFragment();
        detailedFragment.setArguments(bundle);

        mContext.getFragmentManager().beginTransaction()
                .replace(R.id.fragFrame, detailedFragment)
                .addToBackStack("newFrag")
                .commit();
    }

    public class VH extends RecyclerView.ViewHolder {
        @Bind(R.id.date) TextView mDate;
        @Bind(R.id.weatherIcon) ImageView mIcon;
        @Bind(R.id.highTemp)    TextView  mHighTemp;
        @Bind(R.id.lowTemp)     TextView  mLowTemp;
        @Bind(R.id.sumarryItem) View    mSItem;

        public Weather mData;

        public VH(View summary) {
            super(summary);
            ButterKnife.bind(this, summary);
        }

    }

}
