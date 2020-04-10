package com.dotahelper.settings;

import java.util.prefs.Preferences;

public class General {

    public static final int ALERT_10_SECONDS = 10;
    public static final int ALERT_15_SECONDS = 15;
    public static final int ALERT_20_SECONDS = 20;

    protected static Preferences preferences = Preferences.userNodeForPackage(General.class);;

    protected static String alertBeforeSpawn = "rune_notification_seconds";
    private static String dotaRootDir = "integration_path";
    private static String autoMana = "automatic_arcane_boots";
    private static String autoArcaneBootsKey = "automatic_arcane_boots_key";

    public static int getAlertBeforeSpawn() {
        return preferences.getInt(alertBeforeSpawn, ALERT_15_SECONDS);
    }

    public static void setAlertBeforeSpawn(int seconds) {
        preferences.putInt(alertBeforeSpawn, seconds);
    }

    public static String getDotaRootDir() {
        return preferences.get(dotaRootDir, "");
    }

    public static void setDotaRootDir(String path) {
        preferences.put(dotaRootDir, path);
    }

    public static boolean getAutoManaSettings() {
        return preferences.getBoolean(autoMana, false);

    }

    public static void setAutoManaSettings(boolean enableAutoMana) {
        preferences.putBoolean(autoMana, enableAutoMana);
    }

    public static int getAutoArcaneBootsKey() {
        return preferences.getInt(autoArcaneBootsKey, 0);
    }

    public static void setAutoArcaneBootsKey(int key) {
        preferences.putInt(autoArcaneBootsKey, key);
    }
}
