package net.refy.android.g8x.widemode.utils

import android.Manifest
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings

class ActivityUtils(private val context: Context) {

    private val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE)
    private val cActivityManager = activityManager.javaClass
    private val mSetWideMode = cActivityManager.getMethod("setWideScreenMode", Boolean::class.java)
    private val mGetWideMode = cActivityManager.getMethod("getWideScreenMode")
    private val mMoveToDisplayAsDisplayId =
        cActivityManager.getMethod("moveToDisplayAsDisplayId", Int::class.java, Int::class.java)
    private val mMoveToDisplayEx = cActivityManager.getMethod("moveToDisplayEx", Int::class.java)

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

    fun setWideMode(isWide: Boolean) {
        mSetWideMode.invoke(activityManager, isWide)
    }

    fun getWideMode(): Boolean {
        return mGetWideMode.invoke(activityManager) as Boolean
    }

    fun moveToDisplayAsDisplayId(i: Int, i2: Int) {
        mMoveToDisplayAsDisplayId.invoke(activityManager, i, i2)
    }

    fun moveToDisplayEx(i: Int) {
        mMoveToDisplayEx.invoke(activityManager, i)
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
