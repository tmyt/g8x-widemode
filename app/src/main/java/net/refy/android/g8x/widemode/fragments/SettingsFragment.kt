package net.refy.android.g8x.widemode.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import net.refy.android.g8x.widemode.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}