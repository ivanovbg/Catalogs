package com.softomotion.catalogs.core;

import java.util.HashMap;

public interface AppConsts {
    String STATIC_DOMAIN = "https://static.broshura.bg/img/";

    //api end points
    String API_ENDPOINT = "https://api.broshura.bg/";
    String API_CITY_PATH = "/closest_city";
    String API_CITIES_PATH = "/cities";
    String API_PINS_PATH = "/get_map_pins";
    String API_BROCHURES_PATH = "/city_index";
    String API_BROCHURE_PATH = "/brochure_view";

    //database
    String DATABASE_NAME = "brochures.db";


     HashMap<String, Double> WORLD_COORDINATES = new HashMap<String, Double>(){{
        put("top_left_lat", 85.0);
        put("top_left_long", -180.0);
        put("bottom_right_lat", -85.0);
        put("bottom_right_long", 179.999999999);

    }};
}
