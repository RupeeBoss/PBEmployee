package com.android.policyboss.core.controller.variant;

import android.content.Context;

import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.database.RealmDatabaseController;
import com.android.policyboss.core.requestbuilders.VarientMasterRequestBuilder;
import com.android.policyboss.core.response.AllCityMasterResponse;
import com.android.policyboss.core.response.AllMastersResponse;
import com.android.policyboss.core.response.VarientMasterResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import io.realm.Realm;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Rajeev Ranjan on 24/05/2017.
 */

public class VarientMasterController implements IVarientMaster {

    VarientMasterRequestBuilder.VarientMasterNetworkService varientMasterNetworkService;
    Context mContext;
    Realm realm;

    public VarientMasterController(Context context, Realm realm) {
        varientMasterNetworkService = new VarientMasterRequestBuilder().getService();
        mContext = context;
        this.realm = realm;
    }


    @Override
    public void getVarientMaster(final IResponseSubcriber iResponseSubcriber) {
        varientMasterNetworkService.getVarient().enqueue(new Callback<VarientMasterResponse>() {
            @Override
            public void onResponse(retrofit.Response<VarientMasterResponse> response, Retrofit retrofit) {

                if (response.body().getStatusNo() == 0) {
                    //new RealmDatabaseController(realm).insertVariantMaster(response.body().getVariantList());
                    // iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                } else {
                    //iResponseSubcriber.OnFailure(new RuntimeException("Oops,Something went wrong..!"));
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

    @Override
    public void getAllMasters(final IResponseSubcriber iResponseSubcriber) {

        HashMap<String, String> body = new HashMap<String, String>();
        body.put("MasterId", "0");

        varientMasterNetworkService.getAllMasters(body).enqueue(new Callback<AllMastersResponse>() {
            @Override
            public void onResponse(Response<AllMastersResponse> response, Retrofit retrofit) {
                if (response.body().getStatusNo() == 0) {
                    new RealmDatabaseController(realm).insertMasterTables(
                            response.body().getMasterResponse().getLstmake()
                            , response.body().getMasterResponse().getLstmodel()
                            , response.body().getMasterResponse().getLstvariant());
                    iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unable to connect Policyboss server."));
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

    @Override
    public void getAllCityMasters(final IResponseSubcriber iResponseSubcriber) {

        varientMasterNetworkService.getAllCity().enqueue(new Callback<AllCityMasterResponse>() {
            @Override
            public void onResponse(Response<AllCityMasterResponse> response, Retrofit retrofit) {
                if (response.body().getStatusNo() == 0) {
                    new RealmDatabaseController(realm).insertMasterVehicleTables(response.body().getListvehicle());
                    iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unable to connect Policyboss server."));
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
