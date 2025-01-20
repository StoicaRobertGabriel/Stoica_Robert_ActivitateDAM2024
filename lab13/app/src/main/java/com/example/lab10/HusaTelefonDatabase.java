package com.example.lab10;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {HusaTelefon.class}, version = 1, exportSchema = false)
public abstract class HusaTelefonDatabase extends RoomDatabase {
    private static HusaTelefonDatabase instance;

    public abstract HusaTelefonDao husaTelefonDao();

    public static synchronized HusaTelefonDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            HusaTelefonDatabase.class, "husa_telefon_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
