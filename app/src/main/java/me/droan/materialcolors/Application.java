package me.droan.materialcolors;

import android.content.Context;

public class Application extends android.app.Application {

  private static Context context;
  private boolean turnOnFabric = true;

  public static Context getAppContext() {
    return context;
  }

  @Override public void onCreate() {
    super.onCreate();
    if (turnOnFabric) {

    }
    context = getApplicationContext();
  }
}
