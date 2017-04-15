package org.litesgroup;

import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject Application mApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, mApplication.getClass().toString() , Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "testing dependency injection", Toast.LENGTH_LONG).show();
    }

    @Override
    public void inject(LitesApplicationComponent component) {
        component.inject(this);
    }
}
