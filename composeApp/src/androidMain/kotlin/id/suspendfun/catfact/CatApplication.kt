package id.suspendfun.catfact

import android.app.Application
import id.suspendfun.catfact.di.initKoin
import org.koin.android.ext.koin.androidContext

class CatApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@CatApplication)
        }
    }
}