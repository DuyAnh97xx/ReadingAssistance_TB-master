package com.example.builddewarp.overideTalkBack;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;

import java.util.List;

public class PresenterOverideTalkBack
        implements View.OnTouchListener, View.OnHoverListener, PresenterOverrideTalkBackInteface {
    private static final String TAG = "OverrideTalkBack";
    private Context context;
    public PresenterOverideTalkBack(Context context) {
        this.context = context;
    }

    @Override
    public boolean onHover(View v, MotionEvent event) {
        //Move AccessibilityManager object to the constructor
        return talkBackEnable(context) && onTouch(v, event);
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_HOVER_ENTER:
                //Your code to do something
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_HOVER_MOVE:
                //Your code to do something
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_HOVER_EXIT:
                //Your code to do something
                break;

        }
        return true;
    }
    private static final String TALKBACK_SETTING_ACTIVITY_NAME = "com.android.talkback.TalkBackPreferencesActivity";

    @SuppressLint("ObsoleteSdkInt")
    public boolean talkBackEnable(Context context) {
        boolean enable = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            try {
                AccessibilityManager manager = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
                assert manager != null;
                List<AccessibilityServiceInfo> serviceList = manager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_SPOKEN);
                for (AccessibilityServiceInfo serviceInfo : serviceList) {
                    String name = serviceInfo.getSettingsActivityName();
                    if (!TextUtils.isEmpty(name) && name.equals(TALKBACK_SETTING_ACTIVITY_NAME)) {
                        enable = true;
                    }
                }
            } catch (Exception e) {
                Log.d(TAG, "accessibilityEnable: ");
            }
        }
        return enable;
    }
    @Override
    public void DisableTouchForTalkBack(View view) {
        view.setOnHoverListener(this);
    }


}
