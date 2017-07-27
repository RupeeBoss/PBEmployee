package com.android.policyboss.core;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public abstract class GarageRetroRequestBuilder {

    public static String URL = "http://qa.policyboss.com/GarageAppService";

    static Retrofit restAdapter = null;


    protected Retrofit build() {
        if (restAdapter == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient httpClient = new OkHttpClient();
            httpClient.setReadTimeout(360, TimeUnit.SECONDS);
            httpClient.setConnectTimeout(360, TimeUnit.SECONDS);
            httpClient.interceptors().add(logging);
            restAdapter = new Retrofit.Builder()
                    .baseUrl(URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return restAdapter;
    }

}