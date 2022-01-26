package com.gaaan.popularlibraries.mvpuser

import com.gaaan.popularlibraries.data.GitHubUser
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface UserView : MvpView {

    fun showUser(user: GitHubUser)
    fun showError(message: String)

}
