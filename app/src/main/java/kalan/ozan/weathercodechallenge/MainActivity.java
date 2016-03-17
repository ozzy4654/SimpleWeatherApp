package kalan.ozan.weathercodechallenge;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import butterknife.*;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nobrain.android.permissions.AndroidPermissions;
import kalan.ozan.weathercodechallenge.api.WeatherApi;
import kalan.ozan.weathercodechallenge.api.model.Climate;
import kalan.ozan.weathercodechallenge.api.model.MyData;
import kalan.ozan.weathercodechallenge.dialogs.*;
import retrofit.*;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;


public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private boolean         start;
    private String          querySearch;

    public PackageManager packageManager;
    public Progress mProgress;
    boolean askedBefore = false;

    public static final int REQUEST_CODE = 102;

    @Bind(R.id.sub_title)    TextView mSubTitle;
    @Bind(R.id.search_field) EditText mSearch;
    @Bind(R.id.search_btn) Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle(R.string.app_title);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        packageManager = getPackageManager();

        if (packageManager.checkPermission(
                Manifest.permission.ACCESS_FINE_LOCATION,
                getPackageName()) == PackageManager.PERMISSION_DENIED) {

            checkPermission();
        }

        mSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mSearch.getWindowToken(), 0);
                    querySearch = mSearch.getText().toString();
                    getForecast();
                    return true;
                } else {
                    return false;
                }
            }
        });

        if (getIntent().hasCategory(Intent.CATEGORY_LAUNCHER)) {
            start = true;
        } else {
            start = false;
        }
    }

    public void error() {
        mProgress.dismiss();
        SearchError searchError = new SearchError();
        searchError.show(getSupportFragmentManager(), "");
        mBtn.setClickable(true);
        mSearch.setEnabled(true);
    }

    public void showProgress() {
        if (mProgress == null) {
            mProgress = new Progress();
        }
        mProgress.show(getSupportFragmentManager(), "progress");
    }

    @OnClick(R.id.search_btn)
    public void getForecast() {
        showProgress();
        mBtn.setClickable(false);
        mSearch.setEnabled(false);


        Gson gson = new GsonBuilder().create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(getString(R.string.base_api_url))
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        final WeatherApi weatherApi = restAdapter.create(WeatherApi.class);


        weatherApi.getData(querySearch, new Callback<Climate>() {

            @Override public void success(Climate climate, Response response) {
                clearSearch();
                MyData mData = climate.data;
                if (mData.error != null) {
                    error();
                } else {
                    Gson gson = new Gson();
                    String x = gson.toJson(mData);
                    stIntent(x);
                }
            }

            @Override public void failure(RetrofitError error) {
                error();
            }
        });

    }

    public void stIntent(String mdata) {
        mBtn.setClickable(true);
        mSearch.setEnabled(true);
        Intent intent = new Intent(this, CurrentWeatherActivity.class);
        intent.putExtra("DATA", mdata);
        mProgress.dismiss();
        startActivity(intent);
    }

    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @OnClick(R.id.search_field)
    public void clearSearch() {
        mSearch.setText("");
        querySearch = "";
    }


    @Override
    public void onBackPressed() {
        super.finish();
    }

    public void checkPermission() {
        packageManager = getPackageManager();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean showRationale = shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION);

            if (showRationale) {

                RationalPermissionDialog gps = new RationalPermissionDialog();
                gps.show(getSupportFragmentManager(), "");

            } else {
                if (showRationale == false && askedBefore == true && (packageManager.checkPermission(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        getPackageName()) == PackageManager.PERMISSION_DENIED)) {

                    Snackbar snackbar = Snackbar.make(mSubTitle, getResources().getString(
                            R.string.message_no_location_permission_snackbar), Snackbar.LENGTH_INDEFINITE)
                            .setDuration(15000);

                    snackbar.setAction(getResources().getString(R.string.settings), v -> {
                        getSettingsIntent();
                    }).show();
                } else {
                    askedBefore = true;
                    AndroidPermissions.check(this)
                            .permissions(Manifest.permission.ACCESS_FINE_LOCATION)
                            .noPermissions(permissions -> ActivityCompat
                                    .requestPermissions(this,
                                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                                        REQUEST_CODE))
                            .check();
                }
            }
        }
    }

    public static Intent getSettingsIntent() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null);
        intent.setData(uri);
        return intent;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, final String[] permissions, int[] grantResults) {
        AndroidPermissions.result(this)
                .addPermissions(REQUEST_CODE, Manifest.permission.ACCESS_FINE_LOCATION)
                .putActions(REQUEST_CODE, () -> Snackbar.make(mSubTitle, R.string.granted, Snackbar.LENGTH_LONG).show(),
                            (hasPermissions, noPermissions) -> Snackbar.make(mSubTitle, R.string.deny,
                                                                             Snackbar.LENGTH_LONG).show())
                .result(requestCode, permissions, grantResults);
    }

    @Override public void onConnected(Bundle bundle) {

        Location mLastLocation;
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);

        if (mLastLocation != null && start) {
            querySearch = (mLastLocation.getLatitude() + "," + mLastLocation.getLongitude());
            start = false;
            getForecast();
        }
    }

    @Override public void onConnectionSuspended(int i) {

    }

    @Override public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
