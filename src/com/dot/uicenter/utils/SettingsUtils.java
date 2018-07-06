package com.dot.uicenter.utils;

import android.content.Context;
import android.provider.Settings;

import static com.dot.uicenter.utils.ShellUtils.run;

public class SettingsUtils {

    public static void enableAccentTheme(String pkg) {
        run("cmd overlay enable --user 0 " + pkg);
    }

    /*
     * Accent Theme
     * 0 - Teal
     * 1 - Pixel
     * 2 - Green
     * 3 - Yellow
     * 4 - Red
     * 5 - Purple
     * 6 - Grey
     * 7 - Sky
     * 8 - Violet
     * 9 - Pink
     * 10 - Oxygen [Default]
     * 11 - Lemon
     * 12 - Orange
     */
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
     */
    public static void setOverlayTheme(Context context, int val) {
        Settings.System.putInt(context.getContentResolver(),
                Settings.System.SYSTEM_THEME_STYLE, val);
    }

    /*
     * QS Tiles Circle Drawable
     * 0 - Disabled
     * 1 - Enabled [Default]
     */
    public static void setCircleState(Context context, int val) {
        Settings.System.putInt(context.getContentResolver(),
                Settings.System.QS_TILE_CIRCLE, val);
    }
	
	public static int getCircleState(Context context) {
        return Settings.System.getInt(context.getContentResolver(),
					Settings.System.QS_TILE_CIRCLE, 1);
    }


    /*
     * QS Header Color
     */
    public static void setHeaderColor(Context context, String val) {
        Settings.System.putString(context.getContentResolver(),
                Settings.System.QS_HEADER_COLOR, val);
    }

    public static String getHeaderColor(Context context) {
        String headerColor = Settings.System.getString(context.getContentResolver(),
                                  Settings.System.QS_HEADER_COLOR);
        if (headerColor != "")
			return headerColor;
        else
			return "#00FFFFFF";
    }

    /*
     * QSPanel Alpha
     */
    public static void setQSAlpha(Context context, int val) {
        Settings.System.putInt(context.getContentResolver(),
                Settings.System.QS_PANEL_BG_ALPHA, val);
    }

    public static int getQSAlpha(Context context) {
        int alpha = Settings.System.getInt(context.getContentResolver(),
                                  Settings.System.QS_PANEL_BG_ALPHA, 255);
		return alpha;
    }

    /*
     * QSTiles Alpha
     */
    public static void setTilesAlpha(Context context, int val) {
        Settings.System.putInt(context.getContentResolver(),
                Settings.System.QS_TILE_ALPHA, val);
    }

    public static int getTilesAlpha(Context context) {
        int alpha = Settings.System.getInt(context.getContentResolver(),
                                  Settings.System.QS_TILE_ALPHA, 255);
		return alpha;
    }
}