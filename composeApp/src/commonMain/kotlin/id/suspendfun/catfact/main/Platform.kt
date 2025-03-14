package id.suspendfun.catfact.main

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform