package net.refy.android.g8x.widemode.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import net.refy.android.g8x.widemode.services.QslideService

class ToggleWideModeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toggleWideMode()
        finish()
    }

    private fun toggleWideMode() {
        startService(Intent(applicationContext, QslideService::class.java))
    }
}