package org.litesgroup.database;

import androidx.room.Room;
import android.content.Context;

import org.jetbrains.annotations.NotNull;
import org.litesgroup.AppDatabase;
import org.litesgroup.di.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@ApplicationContext @NotNull Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "app.db")
                .build();
    }
}
