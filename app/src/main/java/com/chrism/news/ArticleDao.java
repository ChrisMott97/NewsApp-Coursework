package com.chrism.news;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ArticleDao {
    @Query("SELECT * FROM article")
    List<Article> getAll();

    @Insert
    void insertAll(Article... articles);

    @Insert
    void insert(Article article);

    @Query("DELETE FROM article")
    void deleteAll();
}