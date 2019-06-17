package com.softomotion.catalogs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SPLASHACTIVITY";
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    private LocationManager locationManager;
    private boolean enabled_gps = false;

    private Location mLocation = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (!checkPermissions()) {
            requestPermissions();
        }else{
            getLocation();
        }
    }


    public void onResume() {
        super.onResume();
        if(checkPermissions() && enabled_gps){
            getLocation();
        }else if(enabled_gps){
            goHome("from resume");
        } else if(!checkPermissions() && !enabled_gps){
            requestPermissions();
        }
    }

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);

        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {

            showSnackbar(R.string.permission_rationale, android.R.string.ok,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startLocationPermissionRequest();
                        }
                    });

        } else {
            startLocationPermissionRequest();
        }
    }

    private void startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(SplashActivity.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    private void showSnackbar(final int mainTextStringId, final int actionStringId,
                              View.OnClickListener listener) {
        Snackbar.make(findViewById(android.R.id.content),
                getString(mainTextStringId),
                Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(actionStringId), listener).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
         if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
             if(grantResults.length <= 0){
             } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            }



        }
    }



    private void getLocation() {
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && !enabled_gps) {
            buildAlertMessageNoGps();
            enabled_gps = true;
        }
        try {
            locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, locationListenerGPS, null);
        }catch (SecurityException e){
            Toast.makeText(SplashActivity.this, "ASA",Toast.LENGTH_LONG).show();
        }
    }


    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 1);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                        goHome("dialog cancel");
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }


    LocationListener locationListenerGPS=new LocationListener() {
        @Override
        public void onLocationChanged(android.location.Location location) {
            mLocation = location;
            goHome("location event");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    public void goHome(String text){
        if(mLocation == null){
            Toast.makeText(this, "S grad sofiq be" + text, Toast.LENGTH_LONG).show();
        }else{
            //sad
            double latitude=mLocation.getLatitude();
            double longitude=mLocation.getLongitude();
            String msg="New Latitude: "+latitude + "New Longitude: "+longitude;
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
            Log.d("SPLASH", msg);

            SharedPreferences sharedPref = getSharedPreferences("settings", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("latitude", String.valueOf(latitude));
            editor.putString("longitude", String.valueOf(longitude));
            editor.apply();
        }

        Intent nextScreen = new Intent(this, MainActivity.class);
        nextScreen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
        startActivity(nextScreen);
        ActivityCompat.finishAffinity(this);
    }

}
