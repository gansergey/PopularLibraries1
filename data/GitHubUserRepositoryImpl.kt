package com.gaaan.popularlibraries.data

import com.gaaan.popularlibraries.data.retrofit.GitHubApi
import com.gaaan.popularlibraries.data.room.DBStorage
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GitHubUserRepositoryImpl
@Inject constructor(
    private val gitHubApi: GitHubApi,
    private val roomDb: DBStorage
) : GitHubUserRepository {

    override fun getUsers(): Single<List<GitHubUser>> {
        return roomDb.getGitHubUserDao().getUsers()
            .flatMap {
                if (it.isEmpty()) {
                    gitHubApi.fetchUsers()
                        .map { resultFromServer ->
                            roomDb.getGitHubUserDao().saveUser(resultFromServer)
                            resultFromServer
                        }
                } else {
                    Single.just(it)
                }
            }
    }

    override fun getUserByLogin(login: String): Single<GitHubUser> {
        return roomDb.getGitHubUserDao().getUserByLogin(login)
    }
}