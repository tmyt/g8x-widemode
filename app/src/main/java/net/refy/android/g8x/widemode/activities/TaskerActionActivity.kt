package net.refy.android.g8x.widemode.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import net.refy.android.g8x.widemode.R

class TaskerActionActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_STRING_BLURB = "com.twofortyfouram.locale.intent.extra.BLURB"
        const val EXTRA_BUNDLE = "com.twofortyfouram.locale.intent.extra.BUNDLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var selectedIndex = intent.getIntExtra("mode", -1)
        var dialog: AlertDialog? = null
        dialog = AlertDialog.Builder(this)
            .setTitle(R.string.tasker_plugin_name)
            .setSingleChoiceItems(R.array.tasker_plugin_mode_labels, selectedIndex) { _, i ->
                selectedIndex = i
                dialog!!.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = true
            }
            .setPositiveButton(android.R.string.ok) { _, _ ->
                setResult(RESULT_OK, makeResult(selectedIndex))
                finish()
            }
            .setOnDismissListener {
                setResult(RESULT_CANCELED)
                finish()
            }
            .create()
        dialog.run {
            show()
            getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = selectedIndex >= 0
        }
    }

    private fun makeResult(mode: Int): Intent {
        val bundle = Bundle().apply {
            putInt("mode", mode)
        }
        val blurb = getString(
            when (mode) {
                0 -> R.string.tasker_plugin_mode_on
                1 -> R.string.tasker_plugin_mode_off
                else -> R.string.tasker_plugin_mode_toggle
            }
        )
        return Intent().apply {
            putExtra(EXTRA_STRING_BLURB, blurb)
            putExtra(EXTRA_BUNDLE, bundle)
        }
    }
}