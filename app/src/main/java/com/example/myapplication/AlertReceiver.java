package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlertReceiver extends BroadcastReceiver {

    // The broadcast for notification refreshes scheduled by the app. This is to
    // distinguish the EVENT_REMINDER broadcast sent by the provider.
    public static final String EVENT_REMINDER_APP_ACTION =
            "com.android.calendar.EVENT_REMINDER_APP";

    public static final String ACTION_DISMISS_OLD_REMINDERS = "removeOldReminders";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        throw new UnsupportedOperationException("Not yet implemented");
    }
}