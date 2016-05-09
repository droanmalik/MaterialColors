package me.droan.materialcolors;

import android.content.Context;

public class Application extends android.app.Application {

  private static Context context;

  public static Context getAppContext() {
    return context;
  }

  @Override public void onCreate() {
    super.onCreate();
    context = getApplicationContext();
  }
}
