package net.refy.android.g8x.widemode.activities

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.IBinder
import kotlinx.android.synthetic.main.debug_activity.*
import net.refy.android.g8x.widemode.R
import net.refy.android.g8x.widemode.utils.ActivityUtils

class DebugActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.debug_activity)

        val cServiceManager = Class.forName("android.os.ServiceManager")
        val mGetService = cServiceManager.getMethod("getService", String::class.java)
        val className = "android.hardware.display.IDisplayManagerEx\$Stub"
        val cStub = Class.forName(className)
        val mAsInterface = cStub.getDeclaredMethod("asInterface", IBinder::class.java)
        val iface = mAsInterface.invoke(null, mGetService.invoke(null, "display"))

        //private val displayManager = mAsInterface(null, (context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager).
        //private val mGetCoverDisplayState = displayManager.javaClass.getDeclaredMethod("getCoverDisplayState")

//        val am = getSystemService(Context.WINDOW_SERVICE)
//        val clazz = am.javaClass
//        clazz.methods.forEach {
//            Log.i("XX", it.name)
//        }
//
//        val c = Class.forName("com.lge.config.Features")
//        c.fields.forEach {
//            Log.i("XX", it.name)
//        }

        val activityUtils = ActivityUtils(this)

        val LGContext = Class.forName("com.lge.systemservice.core.LGContext")
        val lgcontext = LGContext.getConstructor(Context::class.java).newInstance(applicationContext)
        val lgPowerManagerHelper =
            LGContext.getMethod("getLGSystemService", String::class.java).invoke(lgcontext, "lgpowermanagerhelper")
        val checkDualDisplayPowerOn =
            lgPowerManagerHelper.javaClass.getMethod("checkDualDisplayPowerOn", Boolean::class.java);
        screen_on.setOnClickListener { checkDualDisplayPowerOn.invoke(lgPowerManagerHelper, true) }
        screen_off.setOnClickListener { checkDualDisplayPowerOn.invoke(lgPowerManagerHelper, false) }
        widemode_on.setOnClickListener { activityUtils.setWideMode(true) }
        widemode_off.setOnClickListener { activityUtils.setWideMode(false) }
        move_to_cover.setOnClickListener { activityUtils.moveToDisplayAsDisplayId(0, 0) }
        move_from_cover.setOnClickListener { activityUtils.moveToDisplayAsDisplayId(1, 0) }
        move_switch.setOnClickListener { activityUtils.moveToDisplayEx(2) }
    }
}