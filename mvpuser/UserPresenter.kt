package com.gaaan.popularlibraries.mvpuser

import com.gaaan.popularlibraries.data.GitHubUserRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class UserPresenter(private val userLogin: String) : MvpPresenter<UserView>() {

    @Inject
    lateinit var userRepository: GitHubUserRepository

    override fun onFirstViewAttach() {
        userRepository
            .getUserByLogin(userLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ user ->
                viewState.showUser(user)
            }, { error ->
                viewState.showError(error.message.toString())
            })
    }
}