package com.gaaan.popularlibraries.data

import com.gaaan.popularlibraries.data.retrofit.GitHubApi
import com.gaaan.popularlibraries.data.room.DBStorage
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GitHubRepoRepositoryImpl
@Inject constructor(
    private val gitHubApi: GitHubApi,
    private val roomDb: DBStorage
) : GitHubRepoRepository {

    override fun getUserRepos(login: String): Single<List<GitHubRepo>> {
        return roomDb.getGitHubRepoDao().getReposByUserLogin(login)
            .flatMap {
                if (it.isEmpty()) {
                    gitHubApi.fetchUserRepositories(login)
                        .map { repos ->
                            val newRepo = repos.map { repo ->
                                repo.ownerLogin = login
                                repo
                            }
                            roomDb.getGitHubRepoDao().insertAll(newRepo)
                            repos
                        }
                } else {
                    Single.just(it)
                }
            }
    }
}