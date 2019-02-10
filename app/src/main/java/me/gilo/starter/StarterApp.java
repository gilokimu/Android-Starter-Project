package me.gilo.starter;

import android.content.Context;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import me.gilo.starter.di.DaggerAppComponent;

public class StarterApp extends DaggerApplication {

    private static Context context = null;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;

        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath(getString(R.string.font_regular))
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());


    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.create();
    }

    public static Context getContext() {
        return context;
    }
}
