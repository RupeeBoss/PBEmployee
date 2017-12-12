package com.android.policyboss.core.requestEntity;

import android.os.Parcel;
import android.os.Parcelable;

public class MemberListEntity implements Parcelable {
    public static final Parcelable.Creator<MemberListEntity> CREATOR = new Parcelable.Creator<MemberListEntity>() {
        @Override
        public MemberListEntity createFromParcel(Parcel source) {
            return new MemberListEntity(source);
        }

        @Override
        public MemberListEntity[] newArray(int size) {
            return new MemberListEntity[size];
        }
    };
    /**
     * MemberDOB : 01-01-1989
     * MemberGender : M
     * MemberNumber : 1
     * MemberType : Adult
     * MemberTypeID : 1
     */

    private String MemberDOB;
    private String MemberGender;
    private String MemberNumber;
    private String MemberType;
    private String MemberTypeID;
    private String MemberRelation;

    public MemberListEntity() {
    }

    protected MemberListEntity(Parcel in) {
        this.MemberDOB = in.readString();
        this.MemberGender = in.readString();
        this.MemberNumber = in.readString();
        this.MemberType = in.readString();
        this.MemberTypeID = in.readString();
        this.MemberRelation = in.readString();
    }

    /**
     * MemberNumber : 1
     * MemberRelation :
     * MemberTypeID : 1
     */


    public String getMemberDOB() {
        return MemberDOB;
    }

    public void setMemberDOB(String MemberDOB) {
        this.MemberDOB = MemberDOB;
    }

    public String getMemberGender() {
        return MemberGender;
    }

    public void setMemberGender(String MemberGender) {
        this.MemberGender = MemberGender;
    }

    public String getMemberNumber() {
        return MemberNumber;
    }

    public void setMemberNumber(String MemberNumber) {
        this.MemberNumber = MemberNumber;
    }

    public String getMemberType() {
        return MemberType;
    }

    public void setMemberType(String MemberType) {
        this.MemberType = MemberType;
    }

    public String getMemberTypeID() {
        return MemberTypeID;
    }

    public void setMemberTypeID(String MemberTypeID) {
        this.MemberTypeID = MemberTypeID;
    }

    public String getMemberRelation() {
        return MemberRelation;
    }

    public void setMemberRelation(String MemberRelation) {
        this.MemberRelation = MemberRelation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.MemberDOB);
        dest.writeString(this.MemberGender);
        dest.writeString(this.MemberNumber);
        dest.writeString(this.MemberType);
        dest.writeString(this.MemberTypeID);
        dest.writeString(this.MemberRelation);
    }
}