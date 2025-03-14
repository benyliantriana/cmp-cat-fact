package id.suspendfun.catfact

import android.os.Build
import id.suspendfun.catfact.main.Platform

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()