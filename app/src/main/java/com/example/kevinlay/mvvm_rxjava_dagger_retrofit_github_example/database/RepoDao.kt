package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

/**
 * Created by kevinlay on 8/18/18.
 */
@Dao
interface RepoDao {
    @Query("SELECT * FROM repos")
    fun getAllRepos(): Flowable<List<Repo>>

    @Query("SELECT repo_name FROM repos WHERE owner_name = :owner")
    fun getReposByOwner(owner: String): Flowable<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepos(repos: List<Repo>)

}