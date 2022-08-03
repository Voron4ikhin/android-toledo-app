package toledo24.pro.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import toledo24.pro.di.appModule
import toledo24.pro.di.dataModule
import toledo24.pro.di.domainModule

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
           // androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModule))
        }
    }
}