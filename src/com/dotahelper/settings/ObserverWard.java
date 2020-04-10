package com.dotahelper.settings;

public class ObserverWard extends General {

    private static String wardAlertSetting = "ward_notification_seconds";

    /**
     * todo
     * @param seconds
     */
    public static void setWardAlertSetting(int seconds) {
        preferences.putInt(wardAlertSetting, seconds);
    }

}
