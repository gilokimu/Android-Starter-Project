package me.gilo.starter.di;

import me.gilo.starter.StarterApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    StarterApp app;

    void AppModule(StarterApp application) {
        app = application;
    }

    @Provides
    @Singleton
    StarterApp providesApplication() {
        return app;
    }

}
