package com.android.policyboss.core.controller.bike;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.models.ResponseEntity;
import com.android.policyboss.core.requestEntity.BikePremiumRequestEntity;
import com.android.policyboss.core.requestEntity.BikeRequestEntity;
import com.android.policyboss.core.requestEntity.SaveAddOnRequestEntity;
import com.android.policyboss.core.requestbuilders.BikeQuotesRequestBuilder;
import com.android.policyboss.core.response.BikePremiumResponse;
import com.android.policyboss.core.response.BikeUniqueResponse;
import com.android.policyboss.core.response.SaveAddOnResponse;
import com.android.policyboss.utility.Constants;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Rajeev Ranjan on 24/05/2017.
 */

public class BikeController implements IBike {

    private static final long SLEEP_DELAY = 10000; // 10 seconds delay.
    BikeQuotesRequestBuilder.BikeQuotesNetworkService bikeQuotesNetworkService;
    Context mContext;
    Handler handler;
    IResponseSubcriber iResponseSubcriber;

    public BikeController(Context context) {
        bikeQuotesNetworkService = new BikeQuotesRequestBuilder().getService();
        mContext = context;
        handler = new Handler();
    }

    @Override
    public void getBikeQuote(BikeRequestEntity bikeRequestEntity, final IResponseSubcriber iResponseSubcriber) {

        bikeQuotesNetworkService.getBikeUniqueID(bikeRequestEntity).enqueue(new Callback<BikeUniqueResponse>() {
            @Override
            public void onResponse(Response<BikeUniqueResponse> response, Retrofit retrofit) {
                if (response.body() != null) {
                    String UNIQUE = response.body().getSummary().getRequest_Unique_Id();

                    SharedPreferences.Editor edit = Constants.getSharedPreferenceEditor(mContext);
                    edit.putString(Constants.BIKEQUOTE_UNIQUEID,
                            UNIQUE);
                    edit.commit();
                    iResponseSubcriber.OnSuccess(response.body(), "");

                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
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
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            new BikeController(mContext).getBikePremium(iResponseSubcriber);
        }
    };

    @Override
    public void getBikePremium(final IResponseSubcriber iResponseSubcriber) {
        this.iResponseSubcriber = iResponseSubcriber;
        BikePremiumRequestEntity entity = new BikePremiumRequestEntity();
        entity.setSecret_key(Constants.SECRET_KEY);
        entity.setClient_key(Constants.CLIENT_KEY);
        entity.setResponse_version(Constants.VERSION_CODE);
        //entity.setExecution_async("no");

        entity.setSearch_reference_number(Constants.getSharedPreference(mContext).getString(Constants.BIKEQUOTE_UNIQUEID, ""));

        if (Constants.getSharedPreference(mContext).getInt(Constants.QUOTE_COUNTER, 0) < 5) {
            Constants.getSharedPreferenceEditor(mContext).putInt(Constants.QUOTE_COUNTER,
                    Constants.getSharedPreference(mContext).getInt(Constants.QUOTE_COUNTER, 0) + 1)
                    .commit();
        }

        bikeQuotesNetworkService.getBikePremiumList(entity).enqueue(new Callback<BikePremiumResponse>() {
            @Override
            public void onResponse(Response<BikePremiumResponse> response, Retrofit retrofit) {
                if (response.body() != null) {

                    BikePremiumResponse bikePremiumResponse = new BikePremiumResponse();
                    if (response.body() != null) {
                        List<ResponseEntity> list = new ArrayList<ResponseEntity>();
                        for (int i = 0; i < response.body().getResponse().size(); i++) {
                            ResponseEntity responseEntity = response.body().getResponse().get(i);
                            if (responseEntity.getError_Code().equals("")) {
                                list.add(responseEntity);
                            }
                        }
                        bikePremiumResponse.setResponse(list);
                        bikePremiumResponse.setSummary(response.body().getSummary());
                    }

                    iResponseSubcriber.OnSuccess(bikePremiumResponse, response.body().getMessage());

                    if (!response.body().getSummary().getStatusX().equals("complete")) {
                        if (Constants.getSharedPreference(mContext).getInt(Constants.QUOTE_COUNTER, 0) < 5) {
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
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });

    }

    @Override
    public void saveAddOn(SaveAddOnRequestEntity saveAddOnRequestEntity, final IResponseSubcriber iResponseSubcriber) {
        saveAddOnRequestEntity.setSecret_key(Constants.SECRET_KEY);
        saveAddOnRequestEntity.setClient_key(Constants.CLIENT_KEY);
        bikeQuotesNetworkService.saveAddOn(saveAddOnRequestEntity).enqueue(new Callback<SaveAddOnResponse>() {
            @Override
            public void onResponse(Response<SaveAddOnResponse> response, Retrofit retrofit) {
                if (response.body() != null) {
                    iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());

                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
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
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });
    }
}
