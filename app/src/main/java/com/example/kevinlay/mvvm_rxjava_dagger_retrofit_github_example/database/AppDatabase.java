package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.database;

/**
 * Created by kevinlay on 8/19/18.
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Repo.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RepoDao repoDao();
}
