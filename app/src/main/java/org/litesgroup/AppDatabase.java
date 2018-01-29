package org.litesgroup;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import org.litesgroup.app.model.Movie;

@Database(entities = {Movie.class}, version = AppDatabase.VERSION, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {

    static final int VERSION = 1;

    private static AppDatabase INSTANCE;

    public abstract MovieDao getMovieDao();
}
