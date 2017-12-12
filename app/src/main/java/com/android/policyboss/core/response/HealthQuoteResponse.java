package com.android.policyboss.core.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.models.HealthQuotesEntity;

import java.util.List;

/**
 * Created by Rajeev Ranjan on 05/06/2017.
 */

public class HealthQuoteResponse extends APIResponse implements Parcelable {
    public static final Parcelable.Creator<HealthQuoteResponse> CREATOR = new Parcelable.Creator<HealthQuoteResponse>() {
        @Override
        public HealthQuoteResponse createFromParcel(Parcel source) {
            return new HealthQuoteResponse(source);
        }

        @Override
        public HealthQuoteResponse[] newArray(int size) {
            return new HealthQuoteResponse[size];
        }
    };
    private List<HealthQuotesEntity> HealthQuotes;

    public HealthQuoteResponse() {
    }


    protected HealthQuoteResponse(Parcel in) {
        this.HealthQuotes = in.createTypedArrayList(HealthQuotesEntity.CREATOR);
    }

    public List<HealthQuotesEntity> getHealthQuotes() {
        return HealthQuotes;
    }

    public void setHealthQuotes(List<HealthQuotesEntity> HealthQuotes) {
        this.HealthQuotes = HealthQuotes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.HealthQuotes);
    }
}
