package com.android.policyboss.core.controller.vairentcontroller;

import android.content.Context;

import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.requestbuilders.VarientMasterRequestBuilder;
import com.android.policyboss.core.requestmodels.VarientMasterRequestEntity;
import com.android.policyboss.core.response.VarientMasterResponse;
import com.android.policyboss.core.responsemodels.VarientEntity;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import io.realm.Realm;
import io.realm.internal.IOException;
import retrofit.Callback;
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
    public void getVarientMaster(VarientMasterRequestEntity varientMasterRequestEntity, final IResponseSubcriber iResponseSubcriber) {
        varientMasterNetworkService.getVarient(varientMasterRequestEntity).enqueue(new Callback<VarientMasterResponse>() {
            @Override
            public void onResponse(retrofit.Response<VarientMasterResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {

                  //  final List<VarientEntity> varientEntities = response.body().;
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {

                            try {
                                realm.deleteAll();
                               // realm.copyToRealmOrUpdate(varientEntities);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            } finally {

                            }
                        }
                    });
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
