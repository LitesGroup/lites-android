package org.litesgroup.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import org.litesgroup.BaseActivity;
import org.litesgroup.playground.PlaygroundActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Start home activity
        startActivity(new Intent(SplashActivity.this, PlaygroundActivity.class));
        // close splash activity
        finish();    }
}