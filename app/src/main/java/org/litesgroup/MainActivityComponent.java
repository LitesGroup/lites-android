package org.litesgroup;

import org.jetbrains.annotations.NotNull;
import org.litesgroup.di.ActivityScope;
import org.litesgroup.di.LitesComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = LitesApplicationComponent.class)
public interface MainActivityComponent extends LitesComponent {
    void inject(@NotNull MainActivity activity);
}
