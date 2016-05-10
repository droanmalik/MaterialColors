package me.droan.materialcolors;

import android.content.Context;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class Application extends android.app.Application {

  private static Context context;
  private boolean turnOnFabric = true;

  public static Context getAppContext() {
    return context;
  }

  @Override public void onCreate() {
    super.onCreate();
    if (turnOnFabric) {
      Fabric.with(this, new Crashlytics());
    }
    context = getApplicationContext();
  }
}
