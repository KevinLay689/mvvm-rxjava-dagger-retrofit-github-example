package com.example.kevinlay.mvvm_rxjava_dagger_retrofit_github_example.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by kevinlay on 8/18/18.
 */
@Entity(tableName = "repos")
data class Repo(@PrimaryKey val uid: String,
                @ColumnInfo(name = "repo_name") val repoName: String,
                @ColumnInfo(name = "owner_name") val owner: String)