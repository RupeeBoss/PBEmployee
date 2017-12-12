package com.android.policyboss.core.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.models.MototrQuotesEntity;

import java.util.List;

/**
 * Created by IN-RB on 31-05-2017.
 */

public class MotorQuotesResponse extends APIResponse implements Parcelable {


    public static final Creator<MotorQuotesResponse> CREATOR = new Creator<MotorQuotesResponse>() {
        @Override
        public MotorQuotesResponse createFromParcel(Parcel in) {
            return new MotorQuotesResponse(in);
        }

        @Override
        public MotorQuotesResponse[] newArray(int size) {
            return new MotorQuotesResponse[size];
        }
    };
    private List<MototrQuotesEntity> MototrQuotes;

    protected MotorQuotesResponse(Parcel in) {
        MototrQuotes = in.createTypedArrayList(MototrQuotesEntity.CREATOR);
    }

    public List<MototrQuotesEntity> getMototrQuotes() {
        return MototrQuotes;
    }

    public void setMototrQuotes(List<MototrQuotesEntity> MototrQuotes) {
        this.MototrQuotes = MototrQuotes;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(MototrQuotes);
    }
}
