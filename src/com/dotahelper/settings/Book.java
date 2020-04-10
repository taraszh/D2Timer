package com.dotahelper.settings;

public class Book extends General {

    private static String bookAlertSetting = "book_notification_seconds";


    public static int getBookRestockInterval() {
        return preferences.getInt(bookAlertSetting, 600);
    }

    /**
     * todo
     * @param seconds
     */
    public static void setBookRestockInterval(int seconds) {
        preferences.putInt(bookAlertSetting, seconds);
    }

}
