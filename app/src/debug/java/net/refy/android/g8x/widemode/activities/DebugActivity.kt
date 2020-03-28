package net.refy.android.g8x.widemode.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ToggleButton
import net.refy.android.g8x.widemode.R
import net.refy.android.g8x.widemode.reflect.ActivityServiceReflect
import net.refy.android.g8x.widemode.reflect.DisplayManagerHelperReflect
import net.refy.android.g8x.widemode.utils.ActivityUtils
import tech.onsen.reflect.Reflect

class DebugActivity : Activity() {
    class LGPowerManagerHelper(context: Context) : Reflect() {
        class LGContext(context: Context) : Reflect() {
            override val type: Class<*> = Class.forName("com.lge.systemservice.core.LGContext")
            override val value = ctor(Context::class.java)(context)
            val getLGSystemService by virtual<Any>(String::class.java)
        }

        private val lgContext = LGContext(context)
        override val value = lgContext.getLGSystemService("lgpowermanagerhelper")
        override val type = value.javaClass
        val checkDualDisplayPowerOn by virtual<Unit>(Boolean::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.debug_activity)

        val activityService = ActivityServiceReflect(this)
        val activityUtils = ActivityUtils(this)
        val lgPowerManagerHelper = LGPowerManagerHelper(this)
        val displayHelperUtils = DisplayManagerHelperReflect(this)

        findViewById<Button>(R.id.screen_on).setOnClickListener { lgPowerManagerHelper.checkDualDisplayPowerOn(true) }
        findViewById<Button>(R.id.screen_off).setOnClickListener { lgPowerManagerHelper.checkDualDisplayPowerOn(false) }
        findViewById<Button>(R.id.widemode_on).setOnClickListener { activityUtils.setWideScreenMode(true) }
        findViewById<Button>(R.id.widemode_off).setOnClickListener { activityUtils.setWideScreenMode(false) }
        findViewById<Button>(R.id.move_to_cover).setOnClickListener { activityService.moveToDisplayAsDisplayId(0, 0) }
        findViewById<Button>(R.id.move_from_cover).setOnClickListener { activityService.moveToDisplayAsDisplayId(1, 0) }
        findViewById<Button>(R.id.move_switch).setOnClickListener { activityService.moveToDisplayEx(2) }
        findViewById<Button>(R.id.launch_on_cover).setOnClickListener {
            activityUtils.startActivityInCoverDisplay(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://google.com/")
                ).apply {
                    addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                })
        }
        findViewById<ToggleButton>(R.id.keep_cover_on).setOnCheckedChangeListener { _, b ->
            displayHelperUtils.keepCoverOnWhenFlipped(b)
        }
    }
}

