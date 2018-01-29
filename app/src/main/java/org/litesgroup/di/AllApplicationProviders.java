package org.litesgroup.di;

import org.litesgroup.database.DatabaseModule;
import org.litesgroup.network.NetworkModule;

import dagger.Module;

@Module(includes = {
        AndroidApplicationModule.class,
        NetworkModule.class,
        DatabaseModule.class

})
public interface AllApplicationProviders {
}
