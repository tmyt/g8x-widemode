package net.refy.android.g8x.widemode.utils

import android.os.IBinder

class DisplayManagerExUtils {
    private val cServiceManager = Class.forName("android.os.ServiceManager")
    private val mGetService = cServiceManager.getMethod("getService", String::class.java)
    private val cStub = Class.forName("android.hardware.display.IDisplayManagerEx\$Stub")
    private val mAsInterface = cStub.getDeclaredMethod("asInterface", IBinder::class.java)
    private val displayManagerEx = mAsInterface.invoke(null, mGetService.invoke(null, "display"))

    private val mGetCoverDisplayState = displayManagerEx.javaClass.getDeclaredMethod("getCoverDisplayState")

    fun getCoverDisplayState(): Int {
        return mGetCoverDisplayState(displayManagerEx) as Int
    }
}