package me.droan.materialcolors;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.bluelinelabs.logansquare.LoganSquare;
import java.io.IOException;
import java.io.InputStream;
import me.droan.materialcolors.adapters.ColorDetailAdapter;
import me.droan.materialcolors.adapters.ColorMainAdapter;
import me.droan.materialcolors.model.MaterialColorModel;

public class ColorsActivity extends AppCompatActivity {

  private static final String EXTRA_COLOR = "color";
  private static final String EXTRA_FROM = "from";
  @Bind(R.id.colorMainList) RecyclerView colorMainList;
  @Bind(R.id.toolbar) TextView toolbar;
  @Bind(R.id.colorDetailList) RecyclerView colorDetailList;
  private ColorDetailAdapter detailAdapter;
  private MaterialColorModel model;

  public static Intent putIntent(Context context, String from) {
    Intent intent = new Intent(context, ColorsActivity.class);
    intent.putExtra(EXTRA_FROM, from);
    return intent;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_colors);
    ButterKnife.bind(this);
    initRecyclerView();
  }

  @Override protected void onResume() {
    super.onResume();
    try {
      InputStream is = getAssets().open("material_colors.json");
      model = LoganSquare.parse(is, MaterialColorModel.class);
      initToolbar(0);
      ColorMainAdapter mainAdapter =
          new ColorMainAdapter(this, model.data, new ColorMainAdapter.ItemClickListener() {
            @Override public void onItemClick(int position) {
              refreshDetailAdapter(position);
            }
          });

      detailAdapter = new ColorDetailAdapter(this, model.data.get(0).colors,
          new ColorDetailAdapter.ItemClickListener() {

            @Override public void onItemClick(String code) {
              SharedPrefUtil.write(getApplicationContext(), getIntent().getStringExtra(EXTRA_FROM),
                  code);
            }
          });

      colorMainList.setAdapter(mainAdapter);
      colorDetailList.setAdapter(detailAdapter);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void refreshDetailAdapter(int position) {
    initToolbar(position);
    detailAdapter.refresh(model.data.get(position).colors);
  }

  private void initRecyclerView() {
    colorMainList.setLayoutManager(new LinearLayoutManager(this));
    colorDetailList.setLayoutManager(new LinearLayoutManager(this));
  }

  private void initToolbar(int position) {
    toolbar.setText(model.data.get(position).name);
  }
}
