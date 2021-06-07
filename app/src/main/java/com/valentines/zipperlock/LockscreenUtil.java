package com.valentines.zipperlock;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;


public class LockscreenUtil {
    private Context mContext = null;
    private static LockscreenUtil mLockscreenUtilInstance;
    public static LockscreenUtil getInstance(Context context) {
        if (mLockscreenUtilInstance == null) {
            if (null != context) {
                mLockscreenUtilInstance = new LockscreenUtil(context);
            }
            else {
                mLockscreenUtilInstance = new LockscreenUtil();
            }
        }
        return mLockscreenUtilInstance;
    }

    private LockscreenUtil() {
        mContext = null;
    }

    private LockscreenUtil(Context context) {
        mContext = context;
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public boolean isStandardKeyguardState() {
        boolean isStandardKeyguqrd = false;
        KeyguardManager keyManager =(KeyguardManager) mContext.getSystemService(mContext.KEYGUARD_SERVICE);
        if (null != keyManager) {
            isStandardKeyguqrd = keyManager.isKeyguardSecure();
        }

        return isStandardKeyguqrd;
    }

    public boolean isSoftKeyAvail(Context context) {
        final boolean[] isSoftkey = {false};
        final View activityRootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int rootViewHeight = activityRootView.getRootView().getHeight();
                int viewHeight = activityRootView.getHeight();
                int heightDiff = rootViewHeight - viewHeight;
                if (heightDiff > 100) {
                    isSoftkey[0] = true;
                }
            }
        });
        return isSoftkey[0];
    }

    public int getStatusBarHeight(){
        int result=0;
        int resourceId= mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(resourceId >0)
            result = mContext.getResources().getDimensionPixelSize(resourceId);

        return result;
    }
}
