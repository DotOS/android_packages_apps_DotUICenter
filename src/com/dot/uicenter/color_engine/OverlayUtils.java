package com.dot.uicenter.color_engine;

import static com.dot.uicenter.utils.ShellUtils.*;

public class OverlayUtils {

    public static void enableAccentTheme(String pkg) {
        run("cmd overlay enable --user 0 " + pkg);
    }
    public static void disableAccentTheme(String pkg) {
        run("cmd overlay disable --user 0 " + pkg);
    }
    /*
    * Main Overlay Theme
    * 0 - Auto
    * 1 - Light
    * 2 - Dark
    * */
    public static void setOverlayTheme(int val) {
        run("settings put secure device_theme " + val);
    }
    /*
    * QS Tiles Tint Mode
    * 0 - Default
    * 1 - Color Accent Track
    * */
    public static void setTintMode(int val) {
        run("settings put secure tint_mode " + val);
    }
}