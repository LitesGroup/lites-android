package org.litesgroup.app.model;

import org.jetbrains.annotations.NotNull;

public class Movie {
    @NotNull String id;
    @NotNull String title;
    int year;

    public Movie(@NotNull String id, @NotNull String title, int year) {
        this.id = id;
        this.title = title;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }
}
