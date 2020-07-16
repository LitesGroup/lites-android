package org.litesgroup.app.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "movie")
public class Movie {
    @PrimaryKey
    @ColumnInfo(name = "movieId")
    @NotNull private String id;

    @ColumnInfo(name = "title")
    @NotNull private String title;

    @ColumnInfo(name = "year")
    private int year;

    public Movie(@NotNull String id, @NotNull String title, int year) {
        this.id = id;
        this.title = title;
        this.year = year;
    }

    @NotNull
    public String getId() {
        return id;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }
}
