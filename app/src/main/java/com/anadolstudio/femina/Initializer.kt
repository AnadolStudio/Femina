package com.anadolstudio.femina

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Process
import com.anadolstudio.femina.di.DI

class Initializer(private val context: Application) {


    fun init() {
        if (isMainProcess()) {
            initInMainProcess()
        }
    }

    private fun initInMainProcess() {
        initDependencyGraph(context)
    }

    // https://stackoverflow.com/a/36256085
    private fun isMainProcess() = context.packageName == getProcessName()

    private fun getProcessName(): String? {
        val myPid = Process.myPid()
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        return manager.runningAppProcesses.firstOrNull { it.pid == myPid }?.processName
    }

    private fun initDependencyGraph(context: Context) {
        DI.init(context = context)
    }

}
