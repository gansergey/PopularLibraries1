package com.gaaan.popularlibraries.dagger

import com.gaaan.popularlibraries.data.GitHubUserRepository
import com.gaaan.popularlibraries.data.GitHubUserRepositoryImpl
import com.gaaan.popularlibraries.data.retrofit.GitHubApi
import com.gaaan.popularlibraries.data.room.DBStorage
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(
        @Named("prod") api: GitHubApi,
        dbStorage: DBStorage
    ): GitHubUserRepository {
        return GitHubUserRepositoryImpl(api, dbStorage)
    }
}