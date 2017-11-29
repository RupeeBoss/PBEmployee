package com.android.policyboss.core.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by IN-RB on 26-11-2017.
 */

public class NotificationMasterEntity extends RealmObject {


    @PrimaryKey
    private long notifyid;
    private String message;
    private String title;
    private String imgUrl;
    private String date;
    private  boolean isread;

    public String getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public long getNotifyid() {
        return notifyid;
    }

    public void setNotifyid(long notifyid) {
        this.notifyid = notifyid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean getIsread() {
        return isread;
    }

    public void setIsread(boolean isread) {
        this.isread = isread;
    }

}
