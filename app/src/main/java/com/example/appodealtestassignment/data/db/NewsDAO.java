package com.example.appodealtestassignment.data.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface NewsDAO {
    @Query("SELECT * FROM 'RssNewsDBEntity'")
    Flowable<List<RssNewsDBEntity>> getAll();

    @Query("SELECT * FROM 'RssNewsDBEntity' WHERE id = :id")
    Single<RssNewsDBEntity> getById(long id);

    @Query("SELECT * FROM 'RssNewsDBEntity' WHERE ui_type = :ui_type")
    Flowable<List<RssNewsDBEntity>> getNewsByUIType(int ui_type);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(RssNewsDBEntity... news);

    @Update
    void update(RssNewsDBEntity news);

    @Delete
    void delete(RssNewsDBEntity news);

}