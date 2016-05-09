package me.droan.materialcolors;

import android.content.Context;
import android.content.SharedPreferences;

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
    SharedPreferences sharedPref = context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPref.edit();
    editor.putString(key, hexCode);
    editor.commit();
  }

  public static String read(Context context, String key, String defaultColor) {
    SharedPreferences sharedPref = context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
    String hexCode = sharedPref.getString(key, defaultColor);
    return hexCode;
  }
}
