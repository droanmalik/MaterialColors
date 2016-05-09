package me.droan.materialcolors;

import android.graphics.Color;

/**
 * Created by drone on 09/05/16.
 */
public class Utility {
  public static int getColor(String hexCode) {
    if (hexCode != null) {
      return Color.parseColor(hexCode);
    } else {
      return 0x000000;
    }
  }

  public static int getColor(boolean white) {
    if (white) {
      return Color.parseColor("#FFFFFF");
    } else {
      return Color.parseColor("#000000");
    }
  }
}
