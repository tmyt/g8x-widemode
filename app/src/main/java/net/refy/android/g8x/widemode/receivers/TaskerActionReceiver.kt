package net.refy.android.g8x.widemode.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import net.refy.android.g8x.widemode.activities.TaskerActionActivity
import net.refy.android.g8x.widemode.utils.ActivityUtils
import net.refy.android.g8x.widemode.utils.DisplayHelperUtils

class TaskerActionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val bundle = intent?.getBundleExtra(TaskerActionActivity.EXTRA_BUNDLE) ?: return
        val mode = bundle.getInt("mode")
        val displayUtils = DisplayHelperUtils(context!!)
        val activityUtils = ActivityUtils(context)
        if (!activityUtils.canSwitchMode() || !displayUtils.isCoverEnabled()) {
            return
        }
        val isInWideMode = displayUtils.getWideScreenMode()
        when (mode) {
            0 -> displayUtils.setWideScreenMode(true)
            1 -> displayUtils.setWideScreenMode(false)
            else -> displayUtils.setWideScreenMode(!isInWideMode)
        }
    }
}