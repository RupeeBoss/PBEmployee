package com.android.policyboss.core.controller.car;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.bike.BikeController;
import com.android.policyboss.core.requestEntity.BikePremiumRequestEntity;
import com.android.policyboss.core.requestEntity.BikeRequestEntity;
import com.android.policyboss.core.requestbuilders.BikeQuotesRequestBuilder;
import com.android.policyboss.core.response.BikePremiumResponse;
import com.android.policyboss.utility.Constants;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Nilesh Birhade on 14-11-2017.
 */

public class CarController implements ICar {

    private static final long SLEEP_DELAY = 5000;//10000; // 10 seconds delay.
    BikeQuotesRequestBuilder.BikeQuotesNetworkService bikeQuotesNetworkService;
    Context mContext;
    Handler handler;
    IResponseSubcriber iResponseSubcriber;

    public CarController(Context context) {
        bikeQuotesNetworkService = new BikeQuotesRequestBuilder().getService();
        mContext = context;
        handler = new Handler();
    }

    @Override
    public void getCarQuote(BikeRequestEntity entity, final IResponseSubcriber iResponseSubcriber) {
//        bikeQuotesNetworkService.getBikeUniqueID(entity).enqueue(new Callback<BikeUniqueResponse>() {
//            @Override
//            public void onResponse(Response<BikeUniqueResponse> response, Retrofit retrofit) {
//                if (response.body() != null) {
//                    String UNIQUE = response.body().getSummary().getRequest_Unique_Id();
//
//                    SharedPreferences.Editor edit = Constants.getSharedPreferenceEditor(mContext);
//                    edit.putString(Constants.CARQUOTE_UNIQUEID,
//                            UNIQUE);
//                    edit.commit();
//                    iResponseSubcriber.OnSuccess(response.body(), "");
//
//                } else {
//                    iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                if (t instanceof ConnectException) {
//                    iResponseSubcriber.OnFailure(t);
//                } else if (t instanceof SocketTimeoutException) {
//                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
//                } else if (t instanceof UnknownHostException) {
//                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
//                } else {
//                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
//                }
//            }
//        });
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            new BikeController(mContext).getBikePremium(iResponseSubcriber);
        }
    };

    @Override
    public void getCarPremium(final IResponseSubcriber iResponseSubcriber) {
        this.iResponseSubcriber = iResponseSubcriber;
        BikePremiumRequestEntity entity = new BikePremiumRequestEntity();
        entity.setSecret_key(Constants.SECRET_KEY);
        entity.setClient_key(Constants.CLIENT_KEY);
        entity.setResponse_version(Constants.VERSION_CODE);

        entity.setSearch_reference_number(Constants.getSharedPreference(mContext).getString(Constants.CARQUOTE_UNIQUEID, ""));

        if (Constants.getSharedPreference(mContext).getInt(Constants.QUOTE_COUNTER, 0) < 3) {
            Constants.getSharedPreferenceEditor(mContext).putInt(Constants.QUOTE_COUNTER,
                    Constants.getSharedPreference(mContext).getInt(Constants.QUOTE_COUNTER, 0) + 1)
                    .commit();
        }

        bikeQuotesNetworkService.getBikePremiumList(entity).enqueue(new Callback<BikePremiumResponse>() {
            @Override
            public void onResponse(Response<BikePremiumResponse> response, Retrofit retrofit) {
                if (response.body() != null) {

                    iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());

                    if (!response.body().getSummary().getStatusX().equals("complete")) {
                        if (Constants.getSharedPreference(mContext).getInt(Constants.QUOTE_COUNTER, 0) < 3) {
                            //server request for pending quotes
                            handler.postDelayed(runnable, SLEEP_DELAY);
                        } else {
                            //remove handler
                            handler.removeCallbacks(runnable);
                            //remove stored counters
                            Constants.getSharedPreferenceEditor(mContext).remove(Constants.QUOTE_COUNTER).commit();
                        }
                    } else {
                        handler.removeCallbacks(runnable);
                    }

                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("No Quote found"));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });

    }
}
