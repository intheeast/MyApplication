package com.example.myapplication;


import android.app.Activity;
import android.app.backup.BackupManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Vibrator;
import androidx.preference.PreferenceCategory;

import android.text.format.Time;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreferenceCompat;

public class MySettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener,
        SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = MySettingsFragment.class.getSimpleName();

    static final String SHARED_PREFS_NAME = "com.example.myapplication.preferences";

    public static final String KEY_HIDE_DECLINED = "preferences_hide_declined";
    public static final String KEY_SHOW_WEEK_NUM = "preferences_show_week_num";
    public static final String KEY_WEEK_START_DAY = "preferences_week_start_day";
    public static final String KEY_HOME_TZ_ENABLED = "preferences_home_tz_enabled";
    public static final String KEY_HOME_TZ = "preferences_home_tz";

    public static final String KEY_ALERTS_CATEGORY = "preferences_alerts_category";
    public static final String KEY_ALERTS = "preferences_alerts";
    public static final String KEY_ALERTS_VIBRATE = "preferences_alerts_vibrate";
    public static final String KEY_ALERTS_POPUP = "preferences_alerts_popup";
    public static final String KEY_DEFAULT_REMINDER = "preferences_default_reminder";

    public static final String KEY_MESSAGE_NOTIFICATION = "preferences_message_notifications";
    public static final String KEY_MESSAGE_FEEDBACK = "preferences_message_feedback";


    // These keys are kept to enable migrating users from previous versions
    private static final String KEY_ALERTS_TYPE = "preferences_alerts_type";
    private static final String ALERT_TYPE_ALERTS = "0";
    private static final String ALERT_TYPE_STATUS_BAR = "1";
    private static final String ALERT_TYPE_OFF = "2";

    CheckBoxPreference mHideDeclined;
    CheckBoxPreference mShowWeekNum;
    ListPreference mWeekStart;
    CheckBoxPreference mUseHomeTZ;
    Preference mHomeTZ;

    CheckBoxPreference mAlert;
    CheckBoxPreference mVibrate;
    CheckBoxPreference mPopup;
    ListPreference mDefaultReminder;

    SwitchPreferenceCompat mMessageNotification;
    Preference mMessageFeedback;

    private String mTimeZoneId;

    /*public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static void setDefaultValues(Context context) {
        PreferenceManager.setDefaultValues(context, SHARED_PREFS_NAME, Context.MODE_PRIVATE,
                R.xml.preferences, false);
    }*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        //addPreferencesFromResource(R.xml.preferences);

        final Activity activity = getActivity();
        //final PreferenceManager preferenceManager = getPreferenceManager();
        final SharedPreferences sharedPreferences = MyAppUtils.getSharedPreferences(getContext(), SHARED_PREFS_NAME);
        //preferenceManager.setSharedPreferencesName(SHARED_PREFS_NAME); // setSharedPreferencesName 호출로 인해 이전 preference 값들이 Preference Screen에 적용되지 않는다.
        //PreferenceManager.getDefaultSharedPreferences(this.getContext());

        final PreferenceScreen preferenceScreen = this.getPreferenceScreen();

        mAlert = (CheckBoxPreference) preferenceScreen.findPreference(KEY_ALERTS);
        mVibrate = (CheckBoxPreference) preferenceScreen.findPreference(KEY_ALERTS_VIBRATE);
        Vibrator vibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator == null || !vibrator.hasVibrator()) {
            Log.i(TAG, "MySettingsFragment:onCreatePreferences:No vibrator!!!");
            PreferenceCategory preferencecategory = (PreferenceCategory) preferenceScreen.findPreference(KEY_ALERTS_CATEGORY);
            preferencecategory.removePreference(mVibrate);
        }

        mPopup = (CheckBoxPreference) preferenceScreen.findPreference(KEY_ALERTS_POPUP);
        mUseHomeTZ = (CheckBoxPreference) preferenceScreen.findPreference(KEY_HOME_TZ_ENABLED);
        mHideDeclined = (CheckBoxPreference) preferenceScreen.findPreference(KEY_HIDE_DECLINED);
        mWeekStart = (ListPreference) preferenceScreen.findPreference(KEY_WEEK_START_DAY);
        mDefaultReminder = (ListPreference) preferenceScreen.findPreference(KEY_DEFAULT_REMINDER);
        mHomeTZ = (Preference) preferenceScreen.findPreference(KEY_HOME_TZ);
        mShowWeekNum = (CheckBoxPreference) preferenceScreen.findPreference(KEY_SHOW_WEEK_NUM);
        mMessageNotification = (SwitchPreferenceCompat) preferenceScreen.findPreference(KEY_MESSAGE_NOTIFICATION);
        mMessageFeedback = (Preference) preferenceScreen.findPreference(KEY_MESSAGE_FEEDBACK);

        mWeekStart.setSummary(mWeekStart.getEntry());
        mDefaultReminder.setSummary(mDefaultReminder.getEntry());

        mTimeZoneId = Time.getCurrentTimezone();

        //SharedPreferences prefs = MyAppUtils.getSharedPreferences(activity,
                //SHARED_PREFS_NAME);

        // Utils.getTimeZone will return the currentTimeZone instead of the one
        // in the shared_pref if home time zone is disabled. So if home tz is
        // off, we will explicitly read it.
        if (!sharedPreferences.getBoolean(KEY_HOME_TZ_ENABLED, false)) {
            mTimeZoneId = sharedPreferences.getString(KEY_HOME_TZ, Time.getCurrentTimezone());
        }

        mHomeTZ.setSummary(mTimeZoneId);

        updateChildPreferences();

        /*
        boolean gotHideDeclined = sharedPreferences.getBoolean(KEY_HIDE_DECLINED, false);
        if (gotHideDeclined) {
            mHideDeclined.setChecked(true);
            Log.i(TAG, "TRUE!!!");
        }
        else {
            mHideDeclined.setChecked(false);
            Log.i(TAG, "FALSE!!!");
        }
        //mHideDeclined.setChecked(sharedPreferences.getBoolean(KEY_HIDE_DECLINED, false));


        if (getHidedeclined) {
            mHideDeclined.setChecked(true);
            Log.i(TAG, "TRUE!!!");
        }
        else {
            mHideDeclined.setChecked(false);
            Log.i(TAG, "FALSE!!!");
        }

        migrateOldPreferences(sharedPreferences);

        updateChildPreferences();
        */
    }

    @Override
    public void onStart() {
        super.onStart();

        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);

        setPreferenceListeners(this);
    }

    @Override
    public void onStop() {
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);

        setPreferenceListeners(null);

        super.onStop();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.i(TAG, "onSharedPreferenceChanged:" + key);

        Activity a = getActivity();
        if (key.equals(KEY_ALERTS)) {
            updateChildPreferences();
            if (a != null) {
                /*
                Intent intent = new Intent();
                intent.setClass(a, AlertReceiver.class);
                if (mAlert.isChecked()) {
                    intent.setAction(AlertReceiver.ACTION_DISMISS_OLD_REMINDERS);
                } else {
                    intent.setAction(AlertReceiver.EVENT_REMINDER_APP_ACTION);
                }
                a.sendBroadcast(intent);
                */
            }
        }
        if (a != null) {
            BackupManager.dataChanged(a.getPackageName());
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        boolean ret = true;

        Log.i(TAG, "MySettingsFragment:onPreferenceChange:" + preference.getKey());

        switch (preference.getKey()) {
            case KEY_HIDE_DECLINED:
            case KEY_SHOW_WEEK_NUM:
            case KEY_HOME_TZ_ENABLED:
            case KEY_ALERTS:
            case KEY_ALERTS_VIBRATE:
            case KEY_ALERTS_POPUP:
            case KEY_MESSAGE_NOTIFICATION:
                MyAppUtils.setSharedPreference(MyAppUtils.getSharedPreferences(getContext(), SHARED_PREFS_NAME), KEY_HIDE_DECLINED, (Boolean)newValue);
                break;
            case KEY_WEEK_START_DAY:
                mWeekStart.setValue((String) newValue);
                mWeekStart.setSummary(mWeekStart.getEntry());
                MyAppUtils.setSharedPreference(MyAppUtils.getSharedPreferences(getContext(), SHARED_PREFS_NAME), KEY_WEEK_START_DAY, (String)newValue);
                break;
            case KEY_DEFAULT_REMINDER:
                mDefaultReminder.setValue((String) newValue);
                mDefaultReminder.setSummary(mDefaultReminder.getEntry());
                MyAppUtils.setSharedPreference(MyAppUtils.getSharedPreferences(getContext(), SHARED_PREFS_NAME), KEY_DEFAULT_REMINDER, (String)newValue);
                break;

            default:
                Log.i(TAG, "MySettingsFragment:onPreferenceChange:what???");
                ret = false;
                break;

        }

        return ret;
    }

    private void setPreferenceListeners(Preference.OnPreferenceChangeListener listener) {
        mHideDeclined.setOnPreferenceChangeListener(listener);
        mShowWeekNum.setOnPreferenceChangeListener(listener);
        mWeekStart.setOnPreferenceChangeListener(listener);
        mUseHomeTZ.setOnPreferenceChangeListener(listener);
        mHomeTZ.setOnPreferenceChangeListener(listener);

        mAlert.setOnPreferenceChangeListener(listener);
        mVibrate.setOnPreferenceChangeListener(listener);
        mPopup.setOnPreferenceChangeListener(listener);
        mDefaultReminder.setOnPreferenceChangeListener(listener);

        mMessageNotification.setOnPreferenceChangeListener(listener);
        mMessageFeedback.setOnPreferenceChangeListener(listener);
    }




    /**
     * If necessary, upgrades previous versions of preferences to the current
     * set of keys and values.
     * @param prefs the preferences to upgrade
     */
    private void migrateOldPreferences(SharedPreferences prefs) {
        // If needed, migrate vibration setting from a previous version
        mVibrate.setChecked(Utils.getDefaultVibrate(getActivity(), prefs));

        // If needed, migrate the old alerts type settin
        if (!prefs.contains(KEY_ALERTS) && prefs.contains(KEY_ALERTS_TYPE)) {
            String type = prefs.getString(KEY_ALERTS_TYPE, ALERT_TYPE_STATUS_BAR);
            if (type.equals(ALERT_TYPE_OFF)) {
                mAlert.setChecked(false);
                mPopup.setChecked(false);
                mPopup.setEnabled(false);
            } else if (type.equals(ALERT_TYPE_STATUS_BAR)) {
                mAlert.setChecked(true);
                mPopup.setChecked(false);
                mPopup.setEnabled(true);
            } else if (type.equals(ALERT_TYPE_ALERTS)) {
                mAlert.setChecked(true);
                mPopup.setChecked(true);
                mPopup.setEnabled(true);
            }
            // clear out the old setting
            prefs.edit().remove(KEY_ALERTS_TYPE).commit();
        }
    }

    private void updateChildPreferences() {
        if (mAlert.isChecked()) {
            mVibrate.setEnabled(true);
            mPopup.setEnabled(true);
        } else {
            mVibrate.setEnabled(false);
            mPopup.setEnabled(false);
        }
    }


    /*
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        boolean ret = false;

        switch (preference.getKey()) {
            case KEY_HIDE_DECLINED:
                if ((Boolean)newValue) {
                    Log.i(TAG, "MySettingsFragment:onPreferenceChange:" + KEY_HIDE_DECLINED + ":" + "true");
                } else {
                    Log.i(TAG, "MySettingsFragment:onPreferenceChange:" + KEY_HIDE_DECLINED + ":" + "false");
                }
                MyAppUtils.setSharedPreference(MyAppUtils.getSharedPreferences(getContext(), SHARED_PREFS_NAME), KEY_HIDE_DECLINED, (Boolean)newValue);
                ret = true;
                break;
            case KEY_SHOW_WEEK_NUM:
                Log.i(TAG, "MySettingsFragment:onPreferenceChange:" + KEY_SHOW_WEEK_NUM);
                MyAppUtils.setSharedPreference(MyAppUtils.getSharedPreferences(getContext(), SHARED_PREFS_NAME), KEY_SHOW_WEEK_NUM, (Boolean)newValue);
                ret = true;
                break;
            case KEY_WEEK_START_DAY:
                Log.i(TAG, "MySettingsFragment:onPreferenceChange:" + KEY_WEEK_START_DAY + ":" + newValue.toString());
                mWeekStart.setValue((String) newValue);
                mWeekStart.setSummary(mWeekStart.getEntry());
                MyAppUtils.setSharedPreference(MyAppUtils.getSharedPreferences(getContext(), SHARED_PREFS_NAME), KEY_WEEK_START_DAY, (String)newValue);
                ret = true;
                break;
            case KEY_HOME_TZ_ENABLED:
                Log.i(TAG, "MySettingsFragment:onPreferenceChange:" + KEY_HOME_TZ_ENABLED);
                MyAppUtils.setSharedPreference(MyAppUtils.getSharedPreferences(getContext(), SHARED_PREFS_NAME), KEY_HOME_TZ_ENABLED, (Boolean)newValue);
                ret = true;
                break;
            case KEY_HOME_TZ:
                Log.i(TAG, "MySettingsFragment:onPreferenceChange:" + KEY_HOME_TZ);
                ret = true;
                break;
            case KEY_ALERTS:
                Log.i(TAG, "MySettingsFragment:onPreferenceChange:" + KEY_ALERTS);
                MyAppUtils.setSharedPreference(MyAppUtils.getSharedPreferences(getContext(), SHARED_PREFS_NAME), KEY_ALERTS, (Boolean)newValue);
                ret = true;
                break;
            case KEY_ALERTS_VIBRATE:
                Log.i(TAG, "MySettingsFragment:onPreferenceChange:" + KEY_ALERTS_VIBRATE);
                MyAppUtils.setSharedPreference(MyAppUtils.getSharedPreferences(getContext(), SHARED_PREFS_NAME), KEY_ALERTS_VIBRATE, (Boolean)newValue);
                ret = true;
                break;
            case KEY_ALERTS_POPUP:
                Log.i(TAG, "MySettingsFragment:onPreferenceChange:" + KEY_ALERTS_POPUP);
                MyAppUtils.setSharedPreference(MyAppUtils.getSharedPreferences(getContext(), SHARED_PREFS_NAME), KEY_ALERTS_POPUP, (Boolean)newValue);
                ret = true;
                break;
            case KEY_DEFAULT_REMINDER:
                Log.i(TAG, "MySettingsFragment:onPreferenceChange:" + KEY_DEFAULT_REMINDER + ":" + newValue.toString());
                mDefaultReminder.setValue((String) newValue);
                mDefaultReminder.setSummary(mDefaultReminder.getEntry());
                MyAppUtils.setSharedPreference(MyAppUtils.getSharedPreferences(getContext(), SHARED_PREFS_NAME), KEY_DEFAULT_REMINDER, (String)newValue);
                ret = true;
                break;

            case KEY_MESSAGE_NOTIFICATION:
                //Log.i(TAG, "MySettingsFragment:onPreferenceChange:" + KEY_MESSAGE_NOTIFICATION);
                if ((Boolean)newValue) {
                    Log.i(TAG, "MySettingsFragment:onPreferenceChange:" + KEY_MESSAGE_NOTIFICATION + ":" + "true");
                } else {
                    Log.i(TAG, "MySettingsFragment:onPreferenceChange:" + KEY_MESSAGE_NOTIFICATION + ":" + "false");
                }
                MyAppUtils.setSharedPreference(MyAppUtils.getSharedPreferences(getContext(), SHARED_PREFS_NAME), KEY_MESSAGE_NOTIFICATION, (Boolean)newValue);
                ret = true;
                break;

            case KEY_MESSAGE_FEEDBACK:
                Log.i(TAG, "MySettingsFragment:onPreferenceChange:" + KEY_MESSAGE_FEEDBACK);
                ret = true;
                break;

            default:
                Log.i(TAG, "MySettingsFragment:onPreferenceChange:what???");
                break;
        }

        return ret;
    }
     */


}

