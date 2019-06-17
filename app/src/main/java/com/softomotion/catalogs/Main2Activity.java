package com.softomotion.catalogs;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;

/**
 * Location sample.
 * <p>
 * Demonstrates use of the Location API to retrieve the last known location for a device.
 */
public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "TESSTTT";

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;

    /**
     * Provides the entry point to the Fused Location Provider API.
     */
    private FusedLocationProviderClient mFusedLocationClient;

    /**
     * Represents a geographical location.
     */
    protected Location mLastLocation;

    private String mLatitudeLabel;
    private String mLongitudeLabel;
    private TextView mLatitudeText;
    private TextView mLongitudeText;
    private boolean gps_check = false;

    LocationManager locationManager;
    Context mContext;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mLatitudeLabel = getResources().getString(R.string.app_name);
        mLongitudeLabel = getResources().getString(R.string.app_name);
        mLatitudeText = (TextView) findViewById((R.id.latitude_text));
        mLongitudeText = (TextView) findViewById((R.id.longitude_text));

        //mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mContext = this;
        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, locationListenerGPS, null);
        isLocationEnabled();
    }

    LocationListener locationListenerGPS=new LocationListener() {
        @Override
        public void onLocationChanged(android.location.Location location) {
            double latitude=location.getLatitude();
            double longitude=location.getLongitude();
            String msg="New Latitude: "+latitude + "New Longitude: "+longitude;
            Toast.makeText(mContext,msg,Toast.LENGTH_LONG).show();
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


    protected void onResume(){
        super.onResume();
        isLocationEnabled();
    }

    private void isLocationEnabled() {

        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(mContext);
            alertDialog.setTitle("Enable Location");
            alertDialog.setMessage("Your locations setting is not enabled. Please enabled it in settings menu.");
            alertDialog.setPositiveButton("Location Settings", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            AlertDialog alert=alertDialog.create();
            alert.show();
        }
        else{
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(mContext);
            alertDialog.setTitle("Confirm Location");
            alertDialog.setMessage("Your Location is enabled, please enjoy");
            alertDialog.setNegativeButton("Back to interface",new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            AlertDialog alert=alertDialog.create();
            alert.show();
        }
    }


//    @Override
//    public void onResume() {
//        super.onResume();
//        getLastLocation();
//    }
//
//
//
//    /**
//     * Provides a simple way of getting a device's location and is well suited for
//     * applications that do not require a fine-grained location and that do not need location
//     * updates. Gets the best and most recent location currently available, which may be null
//     * in rare cases when a location is not available.
//     * <p>
//     * Note: this method should be called after location permission has been granted.
//     */
//    @SuppressWarnings("MissingPermission")
//    private void getLastLocation() {
//        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        Location location= manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//
//        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && !gps_check) {
//            buildAlertMessageNoGps();
//            gps_check = true;
//        }else if(location != null){
////
//                                mLatitudeText.setText(String.format(Locale.ENGLISH, "%s: %f",
//                                        mLatitudeLabel,
//                                        location.getLatitude()));
//                                mLongitudeText.setText(String.format(Locale.ENGLISH, "%s: %f",
//                                        mLongitudeLabel,
//                                        location.getLongitude()));
//        }else{
//            Log.d("TEEEEST", "TESTTT");
//        }
//
//
////
//////        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//////            buildAlertMessageNoGps();
//////        }else {
////            mFusedLocationClient.getLastLocation()
////                    .addOnCompleteListener(this, new OnCompleteListener<Location>() {
////                        @Override
////                        public void onComplete(@NonNull Task<Location> task) {
////                            if (task.isSuccessful() && task.getResult() != null) {
////                                mLastLocation = task.getResult();
////
////                                mLatitudeText.setText(String.format(Locale.ENGLISH, "%s: %f",
////                                        mLatitudeLabel,
////                                        mLastLocation.getLatitude()));
////                                mLongitudeText.setText(String.format(Locale.ENGLISH, "%s: %f",
////                                        mLongitudeLabel,
////                                        mLastLocation.getLongitude()));
////                            }else{
////                                Toast.makeText(Main2Activity.this, "RWEW", Toast.LENGTH_LONG).show();
////                            }
////                        }
////                    });
//////        }
//
//    }
//
//        private void buildAlertMessageNoGps() {
//            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
//                    .setCancelable(false)
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        public void onClick(final DialogInterface dialog, final int id) {
//                            startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 1);
//                        }
//                    })
//                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                        public void onClick(final DialogInterface dialog, final int id) {
//                            dialog.cancel();
//                        }
//                    });
//            final AlertDialog alert = builder.create();
//            alert.show();
//        }
//
//
//
//    /**
//     * Shows a {@link Snackbar} using {@code text}.
//     *
//     * @param text The Snackbar text.
//     */
//    private void showSnackbar(final String text) {
//        View container = findViewById(R.id.main_activity_container);
//        if (container != null) {
//            Snackbar.make(container, text, Snackbar.LENGTH_LONG).show();
//        }
//    }
//
//    /**
//     * Shows a {@link Snackbar}.
//     *
//     * @param mainTextStringId The id for the string resource for the Snackbar text.
//     * @param actionStringId   The text of the action item.
//     * @param listener         The listener associated with the Snackbar action.
//     */
//    private void showSnackbar(final int mainTextStringId, final int actionStringId,
//                              View.OnClickListener listener) {
//        Snackbar.make(findViewById(android.R.id.content),
//                getString(mainTextStringId),
//                Snackbar.LENGTH_INDEFINITE)
//                .setAction(getString(actionStringId), listener).show();
//    }
//
//    /**
//     * Return the current state of the permissions needed.
//     */
//    private boolean checkPermissions() {
//        int permissionState = ActivityCompat.checkSelfPermission(this,
//                Manifest.permission.ACCESS_FINE_LOCATION);
//        return permissionState == PackageManager.PERMISSION_GRANTED;
//    }
//
//    private void startLocationPermissionRequest() {
//        ActivityCompat.requestPermissions(Main2Activity.this,
//                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                REQUEST_PERMISSIONS_REQUEST_CODE);
//    }
//
//    private void requestPermissions() {
//        boolean shouldProvideRationale =
//                ActivityCompat.shouldShowRequestPermissionRationale(this,
//                        Manifest.permission.ACCESS_FINE_LOCATION);
//
//        // Provide an additional rationale to the user. This would happen if the user denied the
//        // request previously, but didn't check the "Don't ask again" checkbox.
//        if (shouldProvideRationale) {
//
//            showSnackbar(R.string.permission_rationale, android.R.string.ok,
//                    new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            startLocationPermissionRequest();
//                        }
//                    });
//
//        } else {
//            startLocationPermissionRequest();
//        }
//    }
//
//    /**
//     * Callback received when a permissions request has been completed.
//     */
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                getLastLocation();
//            }
//        }
//    }


}
