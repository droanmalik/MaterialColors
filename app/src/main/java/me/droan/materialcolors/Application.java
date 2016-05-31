package me.droan.materialcolors;

import android.content.Context;

public class Application extends android.app.Application {

  private static Context context;
  private boolean turnOnLogging = BuildConfig.CRASH_LOGGING_ENABLED;

  public static Context getAppContext() {
    return context;
  }

  @Override public void onCreate() {
    super.onCreate();
    if (turnOnLogging) {

    }
    context = getApplicationContext();
  }
}
