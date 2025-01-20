package com.example.lab10;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface HusaTelefonDao {
    @Insert
    void insert(HusaTelefon husaTelefon);

    @Query("SELECT * FROM husa_telefon_table")
    List<HusaTelefon> getAllHuse();

    @Update
    void update(HusaTelefon husaTelefon);

    @Delete
    void delete(HusaTelefon husaTelefon);

    @Query("DELETE FROM husa_telefon_table")
    void deleteAll();
}
