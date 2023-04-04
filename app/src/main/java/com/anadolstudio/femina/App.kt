package com.anadolstudio.femina

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Initializer(this).init()
    }
}
