package me.droan.materialcolors.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import java.util.ArrayList;
import java.util.List;

@JsonObject public class Data {

  @JsonField public String name;
  @JsonField public String code;
  @JsonField public List<Color> colors = new ArrayList<Color>();
}
