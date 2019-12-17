package net.refy.android.g8x.widemode.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import net.refy.android.g8x.widemode.utils.ActivityUtils
import net.refy.android.g8x.widemode.utils.CoverUtils

class TaskerActionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, bundle: Intent?) {
        val mode = bundle?.extras?.getInt("mode") ?: return
        val coverUtils = CoverUtils()
        val activityUtils = ActivityUtils(context!!)
        if (!activityUtils.canSwitchMode() || !coverUtils.isCoverEnabled()) {
            return
        }
        val isInWideMode = activityUtils.getWideMode()
        when (mode) {
            0 -> activityUtils.setWideMode(true)
            1 -> activityUtils.setWideMode(false)
            else -> activityUtils.setWideMode(!isInWideMode)
        }
    }
}