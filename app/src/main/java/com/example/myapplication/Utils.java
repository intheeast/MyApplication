package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Utils {
    private static final String TAG = "Utils";

    public static final String KEY_ALERTS_VIBRATE_WHEN = "preferences_alerts_vibrateWhen";


    public static boolean getDefaultVibrate(Context context, SharedPreferences prefs) {
        boolean vibrate;
        if (prefs.contains(KEY_ALERTS_VIBRATE_WHEN)) {
            // Migrate setting to new 4.2 behavior
            //
            // silent and never -> off
            // always -> on
            String vibrateWhen = prefs.getString(KEY_ALERTS_VIBRATE_WHEN, null);
            vibrate = vibrateWhen != null && vibrateWhen.equals(context
                    .getString(R.string.prefDefault_alerts_vibrate_true));
            prefs.edit().remove(KEY_ALERTS_VIBRATE_WHEN).commit();
            Log.d(TAG, "Migrating KEY_ALERTS_VIBRATE_WHEN(" + vibrateWhen
                    + ") to KEY_ALERTS_VIBRATE = " + vibrate);
        } else {
            vibrate = prefs.getBoolean(MySettingsFragment.KEY_ALERTS_VIBRATE,
                    false);
        }
        return vibrate;
    }
}
