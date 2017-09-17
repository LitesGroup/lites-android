package org.litesgroup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.jetbrains.annotations.NotNull;
import org.litesgroup.di.LitesComponent;
import org.litesgroup.playground.PlaygroundActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button playgroundButton = (Button) findViewById(R.id.playground_button);
        playgroundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, PlaygroundActivity.class);
                startActivity(intent);
            }
        });
    }

    @NotNull
    @Override
    protected LitesComponent onCreateComponent() {
        return DaggerMainActivityComponent.builder()
                .litesApplicationComponent(LitesApplication.getAppComponent(this))
                .build();
    }

    @Override
    protected void onComponentCreated() {
        super.onComponentCreated();
        final MainActivityComponent component = getComponent();
        component.inject(this);
    }
}
