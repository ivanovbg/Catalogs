package com.softomotion.catalogs.core;

import java.util.HashMap;

public interface AppConsts {
    String STATIC_DOMAIN = "https://static.localhost.bg/img/";

    //api end points
    String API_ENDPOINT = "http://localhost/";
    String API_CITY_PATH = "/closest_city";
    String API_CITIES_PATH = "/cities";
    String API_PINS_PATH = "/get_map_pins";
    String API_BROCHURES_PATH = "/city_index";
    String API_BROCHURE_PATH = "/brochure_view";

    //more
    Integer DEFAULT_CITY_ID = 215; //Sofia
    Integer DEFAULT_THUMB_IMAGE_ID = 0;

    //database
    String DATABASE_NAME = "brochures.db";


    HashMap<String, Double> WORLD_COORDINATES = new HashMap<String, Double>() {{
        put("top_left_lat", 85.0);
        put("top_left_long", -180.0);
        put("bottom_right_lat", -85.0);
        put("bottom_right_long", 179.999999999);

    }};


    //location
    long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 5000;
    int REQUEST_CHECK_SETTINGS = 100;
    int REQUEST_PERMISSIONS_REQUEST_CODE = 34;

    //sharedpreference
    String MY_PREFS = "MY_SETTINGS";
}
