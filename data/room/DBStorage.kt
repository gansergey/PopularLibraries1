package com.gaaan.popularlibraries.data.room
import androidx.room.Database
import androidx.room.RoomDatabase
import com.gaaan.popularlibraries.data.GitHubRepo
import com.gaaan.popularlibraries.data.GitHubUser

@Database(exportSchema = false, entities = [GitHubUser::class, GitHubRepo::class], version = 1)
abstract class DBStorage : RoomDatabase() {

    abstract fun getGitHubUserDao(): GitHubUserDao
    abstract fun getGitHubRepoDao(): GitHubRepoDao
}