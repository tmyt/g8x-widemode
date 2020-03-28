package net.refy.android.g8x.widemode.reflect

import android.Manifest
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import tech.onsen.reflect.Reflect

class ActivityServiceReflect(private val context: Context) : Reflect() {
    override val type by lazy { value.javaClass }
    override val value by lazy { context.getSystemService(Context.ACTIVITY_SERVICE) }
    val moveToDisplayAsDisplayId by virtual<Boolean>(Int::class.java, Int::class.java)
    val moveToDisplayEx by virtual<Boolean>(Int::class.java)
    val setWideScreenMode by virtual<Unit>(Boolean::class.java)
    val getWideScreenMode by virtual<Boolean>()
}
