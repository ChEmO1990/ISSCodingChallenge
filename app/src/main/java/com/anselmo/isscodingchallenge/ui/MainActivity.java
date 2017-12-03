package com.anselmo.isscodingchallenge.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.anselmo.isscodingchallenge.R;
import com.anselmo.isscodingchallenge.api.ISSService;
import com.anselmo.isscodingchallenge.models.ISSResponse;
import com.anselmo.isscodingchallenge.ui.base.BaseActivity;
import com.google.gson.GsonBuilder;
import java.util.List;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity implements View.OnClickListener, EasyPermissions.PermissionCallbacks {
    private static final String LOCATION_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final int RC_LOCATION_PERM = 124;

    private Retrofit mRetrofit;
    private ISSService mApi;

    private Button btnGetCoordinates;
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolBar = getToolbar();
        mToolBar.setTitle(getString(R.string.app_name));

        btnGetCoordinates = (Button) findViewById(R.id.btnGetCoordinates);
        btnGetCoordinates.setOnClickListener(this);

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://api.open-notify.org/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();

        mApi = mRetrofit.create(ISSService.class);
    }

    @AfterPermissionGranted(RC_LOCATION_PERM)
    public void tryToGetCoordinates() {
        if (hasLocationPermission()) {
            // Get user location
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if(location == null) {
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }

            if(location != null) {
                Call<ISSResponse> response = mApi.getPasses(location.getLatitude(), location.getLongitude());
                response.enqueue(new Callback<ISSResponse>() {
                    @Override
                    public void onResponse(Call<ISSResponse> call, Response<ISSResponse> response) {
                        if( response != null ) {
                            Intent intentDetail = new Intent(MainActivity.this, DetailActivity.class);
                            intentDetail.putExtra("model", response.body());
                            startActivity(intentDetail);
                        }
                    }

                    @Override
                    public void onFailure(Call<ISSResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getCause().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        } else {
            EasyPermissions.requestPermissions(this,
                    getString(R.string.rationale_location),
                    RC_LOCATION_PERM,
                    LOCATION_PERMISSION);
        }
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() ) {
            case R.id.btnGetCoordinates: tryToGetCoordinates(); break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, getString(R.string.permission_granted), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, getString(R.string.permission_denied), Toast.LENGTH_SHORT).show();
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {

                String yes = getString(R.string.yes);
                String no = getString(R.string.no);

                Toast.makeText(this, getString(R.string.returned_from_app_settings_to_activity,
                        hasLocationPermission() ? yes : no),
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean hasLocationPermission() {
        return EasyPermissions.hasPermissions(this, LOCATION_PERMISSION);
    }
}