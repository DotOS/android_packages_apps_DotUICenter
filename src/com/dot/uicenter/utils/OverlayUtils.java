package com.dot.uicenter.utils;

import android.content.Context;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.dot.uicenter.MainActivity;

import static com.dot.uicenter.utils.ShellUtils.*;

public class OverlayUtils {

    public static void enableAccentTheme(String pkg) {
        run("cmd overlay enable --user 0 " + pkg);
    }

    /*
    * Accent Theme
    * 0 - Teal [Default]
    * 1 - Pixel
    * 2 - Green
    * 3 - Yellow
    * 4 - Red
    * 5 - Purple
    * 6 - Grey
    * 7 - Sky
    * 8 - Violet
    * 9 - Pink
    * */
    public static void setAccentTheme(Context context, int val) {
        Settings.Secure.putInt(context.getContentResolver(),
                Settings.Secure.ACCENT_THEME, val);
    }

    /*
    * Main Overlay Theme
    * 0 - Auto
    * 1 - Light
    * 2 - Dark
    * 3 - Black
    * */
    public static void setOverlayTheme(Context context, int val) {
        Settings.System.putInt(context.getContentResolver(),
                Settings.System.SYSTEM_THEME_STYLE, val);
    }

    /*
    * QS Tiles Tint Mode
    * 0 - Default
    * 1 - Color Accent Track
    * */
    public static void setTintMode(Context context, int val) {
        Settings.Secure.putInt(context.getContentResolver(),
                Settings.Secure.TINT_MODE, val);
    }


    /*
    * Brightness Warn Mode
    * 0 - Enable
    * 1 - Disable
    * */
    public static void setWarnMode(Context context, int val) {
        Settings.Secure.putInt(context.getContentResolver(),
                Settings.Secure.BRIGHTNESS_WARN_MODE, val);
    }
}