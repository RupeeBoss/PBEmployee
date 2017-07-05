package com.android.policyboss.utility;

import android.content.Context;
import android.os.Handler;

import com.android.policyboss.core.controller.bike.BikeController;

/**
 * Created by Nilesh Birhade on 03-07-2017.
 */

public class QuotePuller implements Runnable {
    Handler handler;
    Context mContext;

    public QuotePuller(Handler handler, Context context) {
        this.handler = handler;
        mContext = context;
        run();
    }

    @Override
    public void run() {
        //10 second delay
        new BikeController(mContext).getBikePremium();

        handler.postDelayed(this, 1000);
    }

    public void removeHandler() {
        handler.removeCallbacks(this);
    }
}
