package com.gaaan.popularlibraries.navigation

import com.gaaan.popularlibraries.mvpuser.UserScreen
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Forward

class OpenDeepLink(private val deepLinkUserid: String) : CustomRouter.Command, Command {

    override fun execute(navigator: CustomNavigator) {
        navigator.applyCommand(Forward(UserScreen(deepLinkUserid)))
    }

}
