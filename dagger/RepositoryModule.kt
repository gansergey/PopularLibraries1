package com.gaaan.popularlibraries.dagger

import com.gaaan.popularlibraries.data.GitHubRepoRepository
import com.gaaan.popularlibraries.data.GitHubRepoRepositoryImpl
import com.gaaan.popularlibraries.data.retrofit.GitHubApi
import com.gaaan.popularlibraries.data.room.DBStorage
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class RepoRepositoryModule {

    @UserFragmentScope
    @Provides
    fun provideRepoRepository(
        @Named("prod") api: GitHubApi,
        dbStorage: DBStorage
    ): GitHubRepoRepository {
        return GitHubRepoRepositoryImpl(api, dbStorage)
    }
}