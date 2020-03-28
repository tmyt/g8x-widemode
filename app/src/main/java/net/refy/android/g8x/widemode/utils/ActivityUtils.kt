package net.refy.android.g8x.widemode.utils

import android.Manifest
import android.app.ActivityOptions
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import net.refy.android.g8x.widemode.R
import net.refy.android.g8x.widemode.reflect.ActivityServiceReflect
import net.refy.android.g8x.widemode.reflect.DisplayManagerHelperReflect

class ActivityUtils(private val context: Context) {

    private val activityService = ActivityServiceReflect(context)
    private val displayManagerHelper = DisplayManagerHelperReflect(context)

    private fun showError(){
        val message = context.getString(
            R.string.error_not_supported_text,
            Build.MODEL, Build.DEVICE
        )
        AlertDialog.Builder(context)
            .setPositiveButton("OK") { _, _ -> }
            .setTitle(R.string.error_not_supported)
            .setMessage(message)
            .create()
            .show()
    }

    fun getWideScreenMode(): Boolean {
        if (!activityService.getWideScreenMode.available) {
            showError()
            return false
        }
        return activityService.getWideScreenMode()
    }

    fun setWideScreenMode(z: Boolean) {
        if (!activityService.setWideScreenMode.available) {
            return showError()
        }
        return activityService.setWideScreenMode(z)
    }

    fun startActivityInMainDisplay(intent: Intent) {
        val options = ActivityOptions.makeBasic()
        options.launchDisplayId = 0
        context.startActivity(intent, options.toBundle())
    }

    fun startActivityInCoverDisplay(intent: Intent) {
        val options = ActivityOptions.makeBasic()
        options.launchDisplayId = displayManagerHelper.getCoverDisplayId()
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