package me.droan.materialcolors.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject public class Color {
  @JsonField public String name;
  @JsonField public String code;
  @JsonField public boolean white;
}
