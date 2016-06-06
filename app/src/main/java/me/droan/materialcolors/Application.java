package me.droan.materialcolors;

import android.content.Context;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class Application extends android.app.Application {

  private static Context context;
  private boolean turnOnLogging = BuildConfig.CRASH_LOGGING_ENABLED;

  public static Context getAppContext() {
    return context;
  }

  @Override public void onCreate() {
    super.onCreate();

    if (turnOnLogging) {
      Fabric.with(this, new Crashlytics());
    }
    context = getApplicationContext();
  }
}
