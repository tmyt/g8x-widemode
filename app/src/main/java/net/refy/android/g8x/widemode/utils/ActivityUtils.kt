package net.refy.android.g8x.widemode.utils

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent

class ActivityUtils(private val context: Context) {

    private val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE)
    private val cActivityManager = activityManager.javaClass
    private val mSetWideMode = cActivityManager.getDeclaredMethod("setWideScreenMode", Boolean::class.java)
    private val mGetWideMode = cActivityManager.getDeclaredMethod("getWideScreenMode")
    private val mMoveToDisplayAsDisplayId =
        cActivityManager.getDeclaredMethod("moveToDisplayAsDisplayId", Int::class.java, Int::class.java)

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
}
