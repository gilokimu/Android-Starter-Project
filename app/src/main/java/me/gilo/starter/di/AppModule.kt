package me.gilo.starter.di

import android.content.Context
import dagger.Module
import dagger.Provides
import me.gilo.starter.StarterApp

import javax.inject.Singleton

@Module
class AppModule(val context: Context) {


    @Provides
    fun context(): Context {
        return context
    }

}
