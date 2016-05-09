package me.droan.materialcolors;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.test.suitebuilder.annotation.Suppress;

public class SharedPrefUtil {
  public static final String KEY_PRIMARY = "primary";
  public static final String KEY_ACCENT = "accent";
  public static final String KEY_STATUS = "status";
  public static final String KEY_BACKGROUND = "background";
  public static final String KEY_BACKGROUND_DARK = "background_dark";
  private static final String MY_PREF = "me.droan.materialColors.sharedpref";

  @Suppress
  public static void write(Context context, String key, String hexCode) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    SharedPreferences.Editor editor = prefs.edit();
    editor.putString(key, hexCode);
    editor.apply();
  }

  @Suppress public static void write(Context context, String key, boolean isWhite) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    SharedPreferences.Editor editor = prefs.edit();
    editor.putBoolean(key, isWhite);
    editor.apply();
  }

  public static String read(Context context, String key, String defaultColor) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    return prefs.getString(key, defaultColor);
  }

  public static boolean read(Context context, String key, boolean isWhite) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    return prefs.getBoolean(key, isWhite);
  }
}
