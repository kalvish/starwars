package com.kalan.starwarsnotebook

import android.app.Application
import com.kalan.starwarsnotebook.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class StarWarsNoteBookAp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@StarWarsNoteBookAp)
            androidLogger()

            modules(appModule)
        }
    }
}