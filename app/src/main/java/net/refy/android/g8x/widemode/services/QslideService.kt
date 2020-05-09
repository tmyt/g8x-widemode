package net.refy.android.g8x.widemode.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import net.refy.android.g8x.widemode.reflect.DisplayManagerHelperReflect
import net.refy.android.g8x.widemode.utils.ActivityUtils

class QslideService : Service() {
    private val handler = Handler()

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handler.postDelayed({
            toggleWideMode()
        }, 500)
        return super.onStartCommand(intent, flags, startId)
    }

    private fun toggleWideMode() {
        val displayUtils = DisplayManagerHelperReflect(this)
        val activityUtils = ActivityUtils(this)
        if (!activityUtils.canSwitchMode() || !displayUtils.isCoverEnabled()) {
            stopSelf()
            return
        }
        val isInWideMode = activityUtils.getWideScreenMode()
        activityUtils.setWideScreenMode(!isInWideMode)
        stopSelf()
    }
}