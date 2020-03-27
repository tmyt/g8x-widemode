package net.refy.android.g8x.widemode.utils

import android.Manifest
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import tech.onsen.reflect.Reflect

class ActivityUtils(private val context: Context) : Reflect() {
    override val type by lazy { value.javaClass }
    override val value by lazy { context.getSystemService(Context.ACTIVITY_SERVICE) }
    val moveToDisplayAsDisplayId by virtual<Boolean>(Int::class.java, Int::class.java)
    val moveToDisplayEx by virtual<Boolean>(Int::class.java)

    private val displayUtils = DisplayHelperUtils(context)

    fun startActivityInMainDisplay(intent: Intent) {
        val options = ActivityOptions.makeBasic()
        options.launchDisplayId = 0
        context.startActivity(intent, options.toBundle())
    }

    fun startActivityInCoverDisplay(intent: Intent) {
        val options = ActivityOptions.makeBasic()
        options.launchDisplayId = displayUtils.getCoverDisplayId()
        context.startActivity(intent, options.toBundle())
    }

    fun canSwitchMode(): Boolean {
        return getGlobalScreenBrightnessMode() == 1 || isSecureWriteable()
    }

    private fun isSecureWriteable(): Boolean {
        return context.checkSelfPermission(Manifest.permission.WRITE_SECURE_SETTINGS) == PackageManager.PERMISSION_GRANTED
    }

    private fun getGlobalScreenBrightnessMode(): Int {
        return Settings.Secure.getInt(context.contentResolver, "global_screen_brightness_mode", 1)
    }
}
