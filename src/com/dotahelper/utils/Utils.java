package com.dotahelper.utils;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

import java.awt.event.KeyEvent;

public class Utils {

    public static String formatTimer(int time) {
        String minFormat;
        String secFormat;
        int min = time / 60;
        int sec = time - (min * 60);

        secFormat = String.valueOf(sec);
        if (secFormat.length() == 1) {
            secFormat = "0" + sec;
        }

        minFormat = String.valueOf(min);
        if (minFormat.length() == 1) {
            minFormat = "0" + min;
        }

        return minFormat + ":" + secFormat;
    }

    public static String getForegroundWindowName() {
        char[] name = new char[512];

        WinDef.HWND hWnd = User32.INSTANCE.GetForegroundWindow();
        User32.INSTANCE.GetWindowText(hWnd, name, name.length);

        return Native.toString(name).toLowerCase();
    }

    public static WinDef.HWND getForegroundWindow() {
        char[] name = new char[512];

        WinDef.HWND hWnd = User32.INSTANCE.GetForegroundWindow();
        User32.INSTANCE.GetWindowText(hWnd, name, name.length);

        return hWnd;
    }

    public static int convertToKeyEvent(String keyName) {
        switch (keyName) {
            case "a":
                return KeyEvent.VK_A;
            case "b":
                return KeyEvent.VK_B;
            case "c":
                return KeyEvent.VK_C;
            case "d":
                return KeyEvent.VK_D;
            case "e":
                return KeyEvent.VK_E;
            case "f":
                return KeyEvent.VK_F;
            case "g":
                return KeyEvent.VK_G;
            case "h":
                return KeyEvent.VK_H;
            case "i":
                return KeyEvent.VK_I;
            case "j":
                return KeyEvent.VK_J;
            case "k":
                return KeyEvent.VK_K;
            case "l":
                return KeyEvent.VK_L;
            case "m":
                return KeyEvent.VK_M;
            case "n":
                return KeyEvent.VK_N;
            case "o":
                return KeyEvent.VK_O;
            case "p":
                return KeyEvent.VK_P;
            case "q":
                return KeyEvent.VK_Q;
            case "r":
                return KeyEvent.VK_R;
            case "s":
                return KeyEvent.VK_S;
            case "t":
                return KeyEvent.VK_T;
            case "u":
                return KeyEvent.VK_U;
            case "v":
                return KeyEvent.VK_V;
            case "w":
                return KeyEvent.VK_W;
            case "x":
                return KeyEvent.VK_X;
            case "y":
                return KeyEvent.VK_Y;
            case "z":
                return KeyEvent.VK_Z;
            case "0":
                return KeyEvent.VK_0;
            case "1":
                return KeyEvent.VK_1;
            case "2":
                return KeyEvent.VK_2;
            case "3":
                return KeyEvent.VK_3;
            case "4":
                return KeyEvent.VK_4;
            case "5":
                return KeyEvent.VK_5;
            case "6":
                return KeyEvent.VK_6;
            case "7":
                return KeyEvent.VK_7;
            case "8":
                return KeyEvent.VK_8;
            case "9":
                return KeyEvent.VK_9;
            case "-":
                return KeyEvent.VK_MINUS;
            case "tab":
                return KeyEvent.VK_TAB;
            case "enter":
                return KeyEvent.VK_ENTER;
            case "space":
                return KeyEvent.VK_SPACE;
            default:
                return 0;
        }
    }

}

