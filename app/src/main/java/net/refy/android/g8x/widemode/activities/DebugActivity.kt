package net.refy.android.g8x.widemode.activities

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.util.Log

class DebugActivity : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val classLoader = ClassLoader.getSystemClassLoader()
        val cServiceManager = classLoader.loadClass("android.os.ServiceManager")
        val mGetService = cServiceManager.getMethod("getService", String::class.java)
        val className = "android.hardware.display.IDisplayManagerEx\$Stub"
        val cStub = classLoader.loadClass(className)
        val mAsInterface = cStub.getDeclaredMethod("asInterface", IBinder::class.java)
        val iface = mAsInterface.invoke(null, mGetService.invoke(null, "display"))

        //private val displayManager = mAsInterface(null, (context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager).
        //private val mGetCoverDisplayState = displayManager.javaClass.getDeclaredMethod("getCoverDisplayState")

        val am = getSystemService(Context.WINDOW_SERVICE)
        val clazz = am.javaClass
        clazz.methods.forEach {
            Log.i("XX", it.name)
        }
    }
}