package org.litesgroup.di;

import org.litesgroup.LitesApplicationComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = LitesApplicationComponent.class)
public interface LitesComponent {
}
