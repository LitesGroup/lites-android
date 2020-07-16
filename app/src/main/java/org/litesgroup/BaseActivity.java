package org.litesgroup;

import android.os.Bundle;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.litesgroup.di.DaggerLitesComponent;
import org.litesgroup.di.LitesComponent;
import org.litesgroup.utils.ObjectUtils;

public abstract class BaseActivity extends AppCompatActivity {
    @Nullable protected LitesComponent mBaseComponent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseComponent = onCreateComponent();
        onComponentCreated();
    }

    @NotNull
    public <T extends LitesComponent> T getComponent() {
        return ObjectUtils.cast(mBaseComponent);
    }

    @NotNull
    protected LitesComponent onCreateComponent() {
        return DaggerLitesComponent.builder()
                .litesApplicationComponent(LitesApplication.getAppComponent(this))
                .build();
    }

    /**
     * Called after onCreateComponent. Can be used for activity dependency injection.
     */
    @CallSuper
    protected void onComponentCreated() {
    }
}
