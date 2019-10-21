package com.serhankhan.posts.di.component

import android.app.Application
import com.serhankhan.posts.BaseApplication
import com.serhankhan.posts.SessionManager
import com.serhankhan.posts.di.module.ViewModelFactoryModule
import com.serhankhan.posts.di.module.ActivityBuildersModule
import com.serhankhan.posts.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * In order to get idea of AndroidInjector
 * We can think AppComponent as a service and BaseApplication class as a client
 * It is effectively setting up Client-Service relationship
 */

/**
 * Annotated as a Singleton so we need AppComponent to be existed
 * as long as the application itself alive
 */

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    ActivityBuildersModule::class,
    AppModule::class, ViewModelFactoryModule::class])
interface AppComponent:AndroidInjector<BaseApplication> {

    var sessionManager:SessionManager

    @Component.Builder
    interface Builder{

        //BindInstance means bind the application class during the construction of the BaseApplication class
        @BindsInstance
        fun application(application:Application):Builder

        fun build():AppComponent
    }

}