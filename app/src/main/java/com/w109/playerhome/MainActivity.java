package com.w109.playerhome;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final String LIBRETUBE_PACKAGE = "com.github.libretube";
    private LinearLayout root;
    private TextView status;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.rgb(20, 20, 20));
        getWindow().setNavigationBarColor(Color.rgb(20, 20, 20));
        getWindow().getDecorView().setSystemUiVisibility(0);
        buildHome();
    }

    private void buildHome() {
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(dp(28), dp(28), dp(28), dp(28));
        root.setBackgroundColor(Color.rgb(20, 20, 20));

        TextView title = new TextView(this);
        title.setText("ВІДЕОПЛЕЄР");
        title.setTextColor(Color.WHITE);
        title.setTextSize(16);
        title.setLetterSpacing(0.12f);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

        TextView subtitle = new TextView(this);
        subtitle.setText("Toscido W109");
        subtitle.setTextColor(Color.rgb(170, 170, 170));
        subtitle.setTextSize(14);
        subtitle.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams subParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        subParams.setMargins(0, dp(8), 0, dp(40));

        Button launch = new Button(this);
        launch.setText("▶   ВІДКРИТИ LIBRETUBE");
        launch.setTextSize(18);
        launch.setAllCaps(false);
        launch.setMinHeight(dp(76));
        launch.setPadding(dp(18), dp(18), dp(18), dp(18));
        launch.setOnClickListener(v -> launchLibreTube());

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.setMargins(0, 0, 0, dp(18));

        status = new TextView(this);
        status.setTextColor(Color.rgb(180, 180, 180));
        status.setTextSize(13);
        status.setGravity(Gravity.CENTER);
        status.setText("Мінімальний домашній екран");
        status.setPadding(dp(8), dp(12), dp(8), dp(12));

        root.addView(title, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        root.addView(subtitle, subParams);
        root.addView(launch, buttonParams);
        root.addView(status, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        setContentView(root);
    }

    private void launchLibreTube() {
        PackageManager pm = getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(LIBRETUBE_PACKAGE);
        if (intent == null) {
            status.setText("LibreTube не знайдено. Перевір, чи він встановлений.");
            Toast.makeText(this, "Не знайдено com.github.libretube", Toast.LENGTH_LONG).show();
            return;
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            status.setText("Не вдалося відкрити LibreTube.");
        }
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
