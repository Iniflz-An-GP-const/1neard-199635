package shejianguo.app1neard.corpapp

import android.app.Application
import shejianguo.app1neard.corpapp.di.dataModule
import shejianguo.app1neard.corpapp.di.dispatcherModule
import shejianguo.app1neard.corpapp.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class SNPCPApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule

        startKoin {
            androidLogger()
            androidContext(this@SNPCPApplication)
            modules(appModules)
        }
    }
}
