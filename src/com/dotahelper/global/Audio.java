package com.dotahelper.global;

public enum Audio {
    DIR         ("audio/"),
    BOUNTY      ("bounty.wav"),
    POWER       ("power.wav"),
    BOOK        ("book.wav"),
    CAMP        ("camp.wav"),
    WARD        ("ward.wav"),
    ;

    private String setting;

    Audio(String setting) {
        this.setting = setting;
    }

    public String getSetting() {
        return setting;
    }
}
