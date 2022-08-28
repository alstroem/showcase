package dk.alstroem.showcase

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShowcaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
