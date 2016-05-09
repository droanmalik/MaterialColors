package me.droan.materialcolors.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import java.util.ArrayList;
import java.util.List;

@JsonObject public class MaterialColorModel {

 @JsonField public List<Data> data = new ArrayList<Data>();
}
