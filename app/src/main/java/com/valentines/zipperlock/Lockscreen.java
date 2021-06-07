package com.valentines.zipperlock;

import android.content.Context;
import android.content.Intent;

import com.valentines.zipperlock.service.LockscreenService;
import com.valentines.zipperlock.service.LockscreenViewService;


public class Lockscreen {
    private Context mContext = null;
    public static final String ISSOFTKEY = "ISSOFTKEY";
    public static final String ISLOCK = "ISLOCK";
    private static Lockscreen mLockscreenInstance;
    public static Lockscreen getInstance(Context context) {
        if (mLockscreenInstance == null) {
            if (null != context) {
                mLockscreenInstance = new Lockscreen(context);
            }
            else {
                mLockscreenInstance = new Lockscreen();
            }
        }
        return mLockscreenInstance;
    }

    private Lockscreen() {
        mContext = null;
    }

    private Lockscreen(Context context) {
        mContext = context;
    }

    public void startLockscreenService() {
        SharedPreferencesUtil.init(mContext);
        Intent startLockscreenIntent =  new Intent(mContext, LockscreenService.class);
        mContext.startService(startLockscreenIntent);

    }
    public void stopLockscreenService() {
        Intent stopLockscreenViewIntent =  new Intent(mContext, LockscreenViewService.class);
        mContext.stopService(stopLockscreenViewIntent);
        Intent stopLockscreenIntent =  new Intent(mContext, LockscreenService.class);
        mContext.stopService(stopLockscreenIntent);
    }
}
