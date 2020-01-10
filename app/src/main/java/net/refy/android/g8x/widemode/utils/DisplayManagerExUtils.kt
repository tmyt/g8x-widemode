package net.refy.android.g8x.widemode.utils

import android.os.IBinder
import net.refy.android.reflect.Reflect

class DisplayManagerExUtils : Reflect() {
    class ServiceManager : Reflect() {
        override val type = Class.forName("android.os.ServiceManager")
        val getService by static<Any>(String::class.java)
    }

    class IDisplayManager_Stub : Reflect() {
        override val type = Class.forName("android.hardware.display.IDisplayManagerEx\$Stub")
        val asInterface by static<Any>(IBinder::class.java)
    }

    private val serviceManager = ServiceManager()
    private val displayManagerStub = IDisplayManager_Stub()

    override val value = displayManagerStub.asInterface(serviceManager.getService("display"))
    override val type: Class<*> = value.javaClass

    val getCoverDisplayState by virtual<Int>()

    fun isCoverEnabled() = getCoverDisplayState() == 3
}