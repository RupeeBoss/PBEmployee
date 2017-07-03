package com.android.policyboss.utility;

import android.os.Handler;
import android.util.Log;

/**
 * Created by Nilesh Birhade on 03-07-2017.
 */

public class QuotePuller implements Runnable {
    Handler handler;

    public QuotePuller(Handler handler) {
        this.handler = handler;
        run();
    }

    @Override
    public void run() {
        Log.d("QuotePuller", "QuotePuller");
        handler.postDelayed(this, 10000);
    }

    public void removeHandler() {
        handler.removeCallbacks(this);
    }
}
