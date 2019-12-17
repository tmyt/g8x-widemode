package net.refy.android.g8x.widemode.services

import android.Manifest
import android.content.pm.PackageManager
import android.os.RemoteException
import android.provider.Settings
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.widget.Toast
import androidx.annotation.StringRes
import net.refy.android.g8x.widemode.R
import net.refy.android.g8x.widemode.utils.ActivityUtils
import net.refy.android.g8x.widemode.utils.CoverUtils
import net.refy.android.g8x.widemode.utils.StatusBarUtils

class QSTileService : TileService() {
    private lateinit var activityUtils: ActivityUtils
    private lateinit var coverUtils: CoverUtils
    private lateinit var statusBarUtils: StatusBarUtils
    private var isWide = false

    override fun onCreate() {
        super.onCreate()
        activityUtils = ActivityUtils(applicationContext)
        coverUtils = CoverUtils()
        statusBarUtils = StatusBarUtils(applicationContext)
    }

    override fun onClick() {
        super.onClick()
        if(activityUtils.canSwitchMode()){
            try {
                activityUtils.setWideMode(!isWide)
                isWide = !isWide
            } catch (e: RemoteException) {
                showToast(R.string.error_remote_exception)
            }
        }else{
            showToast(R.string.error_no_secure_writeable)
        }
        updateState()
        statusBarUtils.collapsePanels()
    }

    override fun onStartListening() {
        super.onStartListening()
        isWide = activityUtils.getWideMode()
        updateState()
    }

    private fun updateState() {
        qsTile.state = when {
            !coverUtils.isCoverEnabled() -> Tile.STATE_UNAVAILABLE
            isWide -> Tile.STATE_ACTIVE
            else -> Tile.STATE_INACTIVE
        }
        qsTile.updateTile()
    }

    private fun showToast(@StringRes resId: Int) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show()
    }
}