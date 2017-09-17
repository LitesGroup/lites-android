package org.litesgroup.playground;

import org.jetbrains.annotations.NotNull;
import org.litesgroup.LitesApplicationComponent;
import org.litesgroup.MainActivity;
import org.litesgroup.di.ActivityScope;
import org.litesgroup.di.LitesComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = LitesApplicationComponent.class)
public interface PlaygroundComponent extends LitesComponent {
    void inject(@NotNull PlaygroundActivity activity);
}