package me.droan.materialcolors.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import java.util.ArrayList;
import java.util.List;

@JsonObject public class Data {
  @JsonField public List<Red> red = new ArrayList<>();
  @JsonField public List<Pink> pink = new ArrayList<>();
}
