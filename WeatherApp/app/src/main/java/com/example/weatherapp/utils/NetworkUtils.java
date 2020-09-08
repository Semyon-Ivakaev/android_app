package com.example.weatherapp.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    private static final String WEATHER_API_BASE_URL = "http://api.openweathermap.org/data/2.5";
    private static final String WEATHER_RESPONSE = "/weather";
    private static final String PARAM_CITY_NAME = "q";
    private static final String ACCESS_TOKEN = "appid";
    private static final String TOKEN = "1b2401d2283ac2132c3beb19b5c67d3e";

    public static URL generateURL(String cityName) {
        Uri buildUri = Uri.parse(WEATHER_API_BASE_URL + WEATHER_RESPONSE)
                .buildUpon()
                .appendQueryParameter(PARAM_CITY_NAME, cityName)
                .appendQueryParameter(ACCESS_TOKEN, TOKEN)
                .build();

        URL url = null;
        try {
            url = new URL(buildUri.toString()); // это в трай заворачивать просит сама студия. ТАК НАДО!
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream inputStream = urlConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();

            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
