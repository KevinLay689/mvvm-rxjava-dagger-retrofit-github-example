package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.dagger;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.database.AppDatabase;
import com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.database.RepoDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kevinlay on 8/19/18.
 */
@Module
public class DatabaseModule {

    AppDatabase appDatabase;

    public DatabaseModule(Application application) {
        appDatabase = Room.databaseBuilder(application, AppDatabase.class, "repos").build();
    }

    @Provides
    @Singleton
    public AppDatabase provideAppDatabase2() {
        return appDatabase;
    }

    @Provides
    @Singleton
    public RepoDao provideRepoDao() {
        return appDatabase.repoDao();
    }
}
