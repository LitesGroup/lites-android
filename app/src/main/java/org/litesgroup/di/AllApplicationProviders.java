package org.litesgroup.di;

import org.litesgroup.network.NetworkModule;

import dagger.Module;

@Module(includes = {
        AndroidApplicationModule.class,
        NetworkModule.class

})
public interface AllApplicationProviders {
}
