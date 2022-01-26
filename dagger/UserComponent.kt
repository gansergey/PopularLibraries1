package com.gaaan.popularlibraries.dagger

import com.gaaan.popularlibraries.mvpuser.UserPresenter
import dagger.Subcomponent
import javax.inject.Scope

@UserFragmentScope
@Subcomponent(modules = [RepoRepositoryModule::class])
interface UserComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): UserComponent
    }

    fun inject(activity: UserPresenter)
}

@Scope
annotation class UserFragmentScope