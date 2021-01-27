package com.example.appodealtestassignment.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {RssNewsDBEntity.class}, version = 2)
public abstract class NewsDB extends RoomDatabase {
    public abstract NewsDAO NewsDAO();

}