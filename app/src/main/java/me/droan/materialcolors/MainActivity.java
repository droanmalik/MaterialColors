package me.droan.materialcolors;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
    implements SharedPreferences.OnSharedPreferenceChangeListener {

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.appBar) AppBarLayout appBar;
  @Bind(R.id.colorPrimary1) Button colorPrimary1;
  @Bind(R.id.colorPrimary2) Button colorPrimary2;
  @Bind(R.id.colorStatus1) Button colorStatus1;
  @Bind(R.id.colorStatus2) Button colorStatus2;
  @Bind(R.id.colorAccent1) Button colorAccent1;
  @Bind(R.id.colorAccent2) Button colorAccent2;
  @Bind(R.id.colorBackground1) Button colorBackground1;
  @Bind(R.id.colorBackground2) Button colorBackground2;
  @Bind(R.id.gridLayout) GridLayout gridLayout;
  @Bind(R.id.settings) Button settings;
  @Bind(R.id.fab) FloatingActionButton fab;
  @Bind(R.id.root) CoordinatorLayout root;

  private String colorPrimary;
  private String colorAccent;
  private String colorBackground;
  private String colorStatusBar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  private void getSharedPrefData() {
    PreferenceManager.getDefaultSharedPreferences(this)
        .registerOnSharedPreferenceChangeListener(this);
    colorPrimary = SharedPrefUtil.read(this, SharedPrefUtil.KEY_PRIMARY,
        getFormat(getResources().getColor(R.color.colorPrimary)));
    colorAccent = SharedPrefUtil.read(this, SharedPrefUtil.KEY_ACCENT,
        getFormat(getResources().getColor(R.color.colorAccent)));
    colorBackground = SharedPrefUtil.read(this, SharedPrefUtil.KEY_BACKGROUND,
        getFormat(getResources().getColor(android.R.color.white)));
    //colorStatusBar = SharedPrefUtil.read(this, SharedPrefUtil.KEY_STATUS,
    //    getFormat(getResources().getColor(android.R.color.black)));
    Log.e("121212", "primary" + colorPrimary);
    Log.e("121212", "accent" + colorAccent);
    Log.e("121212", "back" + colorBackground);

  }

  @Override protected void onResume() {
    super.onResume();
    getSharedPrefData();
    updateViews();
  }

  private void updateViews() {
    fab.setBackgroundTintList(ColorStateList.valueOf(Utility.getColor(colorAccent)));
    gridLayout.setBackgroundColor(Utility.getColor(colorBackground));
    toolbar.setBackgroundColor(Utility.getColor(colorPrimary));
    // finally change the color
    //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    //  Window window = getWindow();
    //
    //  // clear FLAG_TRANSLUCENT_STATUS flag:
    //  window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    //
    //  // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
    //  window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    //
    //  // finally change the color
    //  window.setStatusBarColor(getResources().getColor(Utility.getColor(colorStatusBar)));
    //}
  }

  private String getFormat(int color) {
    return String.format("#%06X", (0xFFFFFF & color));
  }

  @OnClick({
      R.id.colorPrimary1, R.id.colorPrimary2, R.id.colorStatus1, R.id.colorStatus2,
      R.id.colorAccent1, R.id.colorAccent2, R.id.colorBackground1, R.id.colorBackground2,
      R.id.settings
  }) public void onClick(View view) {
    switch (view.getId()) {
      case R.id.colorPrimary1:
      case R.id.colorPrimary2:
        Intent intent = ColorsActivity.putIntent(this, SharedPrefUtil.KEY_PRIMARY);
        startActivity(intent);
        break;
      case R.id.colorStatus1:
      case R.id.colorStatus2:
        break;
      case R.id.colorAccent1:
      case R.id.colorAccent2:
        Intent intent1 = ColorsActivity.putIntent(this, SharedPrefUtil.KEY_ACCENT);
        startActivity(intent1);
        break;
      case R.id.colorBackground1:
      case R.id.colorBackground2:
        Intent intent2 = ColorsActivity.putIntent(this, SharedPrefUtil.KEY_BACKGROUND);
        startActivity(intent2);
        break;
      case R.id.settings:
        Toast.makeText(MainActivity.this, "Coming soon...", Toast.LENGTH_SHORT).show();
        break;
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    PreferenceManager.getDefaultSharedPreferences(this)
        .unregisterOnSharedPreferenceChangeListener(this);
  }

  @Override public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    getSharedPrefData();
    updateViews();
  }
}