package me.droan.materialcolors;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by drone on 09/05/16.
 */
public class SharedPrefUtil {
  public static final String KEY_PRIMARY = "primary";
  public static final String KEY_ACCENT = "accent";
  public static final String KEY_STATUS = "status";
  public static final String KEY_BACKGROUND = "primary";
  private static final String MY_PREF = "me.droan.materialColors.sharedpref";

  public static void write(Context context, String key, String hexCode) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    SharedPreferences.Editor editor = prefs.edit();
    editor.putString(key, hexCode);
    editor.commit();
  }

  public static String read(Context context, String key, String defaultColor) {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    String hexCode = prefs.getString(key, defaultColor);
    return hexCode;
  }
}
