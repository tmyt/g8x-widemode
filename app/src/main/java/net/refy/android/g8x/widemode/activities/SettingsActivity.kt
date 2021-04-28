package net.refy.android.g8x.widemode.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.refy.android.g8x.widemode.R
import net.refy.android.g8x.widemode.fragments.SettingsFragment

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settingsContainer, SettingsFragment())
            .commit()
    }
}