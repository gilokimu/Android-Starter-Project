package me.gilo.starter.di

import dagger.Module
import dagger.Provides
import me.gilo.starter.StarterApp

import javax.inject.Singleton

@Module
class AppModule {

    internal lateinit var app: StarterApp

    internal fun AppModule(application: StarterApp) {
        app = application
    }

    @Provides
    @Singleton
    internal fun providesApplication(): StarterApp {
        return app
    }

}
