package me.gilo.starter

import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import me.gilo.starter.di.DaggerAppComponent

class StarterApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath(getString(R.string.font_regular))
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                )
                .build()
        )


    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.create()
    }
}
