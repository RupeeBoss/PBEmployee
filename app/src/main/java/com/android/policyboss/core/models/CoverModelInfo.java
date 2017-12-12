package com.android.policyboss.core.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nilesh Birhade on 05-06-2017.
 */

public class CoverModelInfo implements Parcelable {

    public static final Parcelable.Creator<CoverModelInfo> CREATOR = new Parcelable.Creator<CoverModelInfo>() {
        @Override
        public CoverModelInfo createFromParcel(Parcel source) {
            return new CoverModelInfo(source);
        }

        @Override
        public CoverModelInfo[] newArray(int size) {
            return new CoverModelInfo[size];
        }
    };
    int cover; // 1 self  2 family 3 parents
    int coverForSelf; // 1 married 2 unMarried
    int coverForFamily; // 1 self 2 spouse 3 Both
    int coverForParents; //1 father 2 Mother 3 Both
    int NoofKids; // no

    public CoverModelInfo() {
    }

    protected CoverModelInfo(Parcel in) {
        this.cover = in.readInt();
        this.coverForSelf = in.readInt();
        this.coverForFamily = in.readInt();
        this.coverForParents = in.readInt();
        this.NoofKids = in.readInt();
    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }

    public int getCoverForSelf() {
        return coverForSelf;
    }

    public void setCoverForSelf(int coverForSelf) {
        this.coverForSelf = coverForSelf;
    }

    public int getCoverForFamily() {
        return coverForFamily;
    }

    public void setCoverForFamily(int coverForFamily) {
        this.coverForFamily = coverForFamily;
    }

    public int getCoverForParents() {
        return coverForParents;
    }

    public void setCoverForParents(int coverForParents) {
        this.coverForParents = coverForParents;
    }

    public int getNoofKids() {
        return NoofKids;
    }

    public void setNoofKids(int noofKids) {
        NoofKids = noofKids;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.cover);
        dest.writeInt(this.coverForSelf);
        dest.writeInt(this.coverForFamily);
        dest.writeInt(this.coverForParents);
        dest.writeInt(this.NoofKids);
    }
}
