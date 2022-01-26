package com.gaaan.popularlibraries.mvpusers

import com.gaaan.popularlibraries.data.GitHubUser
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface UsersView : MvpView {

    fun showUsers(users: List<GitHubUser>)
    fun showError(message: String)

}