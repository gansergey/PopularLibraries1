package com.gaaan.popularlibraries.dagger

import android.content.Context
import com.gaaan.popularlibraries.MainActivity
import com.gaaan.popularlibraries.mvpuser.UserPresenter
import com.gaaan.popularlibraries.mvpusers.UsersPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        CiceroneModule::class,
        RepositoryModule::class,
        RoomModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun setContext(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(activity: UsersPresenter)
    fun inject(activity: UserPresenter)

}
