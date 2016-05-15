package me.droan.materialcolors;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.droan.materialcolors.fontUtil.FontCache;

public class MainActivity extends AppCompatActivity
    implements SharedPreferences.OnSharedPreferenceChangeListener {

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.appBar) AppBarLayout appBar;
  @Bind(R.id.colorPrimary1) TextView colorPrimary1;
  @Bind(R.id.colorPrimary2) TextView colorPrimary2;
  @Bind(R.id.colorStatus1) Button colorStatus1;
  @Bind(R.id.colorStatus2) Button colorStatus2;
  @Bind(R.id.colorAccent1) TextView colorAccent1;
  @Bind(R.id.colorAccent2) TextView colorAccent2;
  @Bind(R.id.colorBackground1) TextView colorBackground1;
  @Bind(R.id.colorBackground2) TextView colorBackground2;
  @Bind(R.id.gridLayout) GridLayout gridLayout;
  @Bind(R.id.share) Button share;
  @Bind(R.id.fab) FloatingActionButton fab;
  @Bind(R.id.root) CoordinatorLayout root;

  private String colorPrimary;
  private String colorAccent;
  private String colorBackground;
  private String colorStatusBar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    FontCache.getInstance().addFont("robotoMono", "RobotoMono400.ttf");
    DataBindingUtil.setContentView(this, R.layout.activity_main);
    ButterKnife.bind(this);
  }

  private void getSharedPrefData() {
    PreferenceManager.getDefaultSharedPreferences(this)
        .registerOnSharedPreferenceChangeListener(this);
    colorPrimary = SharedPrefUtil.read(this, SharedPrefUtil.KEY_PRIMARY,
        getFormat(ContextCompat.getColor(this, R.color.colorPrimary)));
    colorAccent = SharedPrefUtil.read(this, SharedPrefUtil.KEY_ACCENT,
        getFormat(ContextCompat.getColor(this, R.color.colorAccent)));
    colorBackground = SharedPrefUtil.read(this, SharedPrefUtil.KEY_BACKGROUND,
        getFormat(ContextCompat.getColor(this, R.color.white)));
  }

  @Override protected void onResume() {
    super.onResume();
    getSharedPrefData();
    updateViews();
  }

  private void updateViews() {
    fab.setBackgroundTintList(ColorStateList.valueOf(Utility.getColor(colorAccent)));
    root.setBackgroundColor(Utility.getColor(colorBackground));
    toolbar.setBackgroundColor(Utility.getColor(colorPrimary));
    colorPrimary2.setTextColor(Utility.getColor(colorPrimary));
    colorPrimary2.setText(colorPrimary);
    colorAccent2.setTextColor(Utility.getColor(colorAccent));
    colorAccent2.setText(colorAccent);
    colorBackground2.setText(colorBackground);
    boolean isWhite =
        SharedPrefUtil.read(getApplicationContext(), SharedPrefUtil.KEY_BACKGROUND_DARK, false);
    int black = ContextCompat.getColor(this, R.color.black);
    int white = ContextCompat.getColor(this, R.color.white);
    if (!isWhite) {
      colorAccent1.setTextColor(black);
      colorPrimary1.setTextColor(black);
      colorBackground1.setTextColor(black);
      colorBackground2.setTextColor(black);
    } else {
      colorAccent1.setTextColor(white);
      colorPrimary1.setTextColor(white);
      colorBackground1.setTextColor(white);
      colorBackground2.setTextColor(white);
    }
  }

  private String getFormat(int color) {
    return String.format("#%06X", (0xFFFFFF & color));
  }

  @OnClick({
      R.id.colorPrimary1, R.id.colorPrimary2, R.id.colorStatus1, R.id.colorStatus2,
      R.id.colorAccent1, R.id.colorAccent2, R.id.colorBackground1, R.id.colorBackground2,
      R.id.share, R.id.toolbar, R.id.fab
  }) public void onClick(View view) {
    switch (view.getId()) {
      case R.id.colorPrimary1:
      case R.id.colorPrimary2:
      case R.id.toolbar:
        Intent intent = ColorsActivity.putIntent(this, SharedPrefUtil.KEY_PRIMARY);
        startActivity(intent);
        break;
      case R.id.colorStatus1:
      case R.id.colorStatus2:
        break;
      case R.id.colorAccent1:
      case R.id.colorAccent2:
      case R.id.fab:
        Intent intent1 = ColorsActivity.putIntent(this, SharedPrefUtil.KEY_ACCENT);
        startActivity(intent1);
        break;
      case R.id.colorBackground1:
      case R.id.colorBackground2:
        Intent intent2 = ColorsActivity.putIntent(this, SharedPrefUtil.KEY_BACKGROUND);
        startActivity(intent2);
        break;
      case R.id.share:
        shareViaEmail();
        break;
    }
  }

  private void shareViaEmail() {
    String subject = "#MaterialColors";
    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "", null));
    emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
    String text = "colorPrimary: "
        + colorPrimary
        + "\n"
        + "colorAccent: "
        + colorAccent
        + "\n"
        + "colorBackground: "
        + colorBackground;
    emailIntent.putExtra(Intent.EXTRA_TEXT, text);
    if (emailIntent.resolveActivity(getPackageManager()) != null) {
      startActivity((Intent.createChooser(emailIntent, "Share palettes via email...")));
    } else {
      Toast.makeText(this, "No email client found", Toast.LENGTH_SHORT).show();
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