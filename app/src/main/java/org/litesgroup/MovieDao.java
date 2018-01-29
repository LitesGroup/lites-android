package org.litesgroup;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import org.jetbrains.annotations.NotNull;
import org.litesgroup.app.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert
    public void inserMovie(@NotNull Movie movie);

    @Query("SELECT * FROM MOVIE")
    public List<Movie> getAllMovies();
}
