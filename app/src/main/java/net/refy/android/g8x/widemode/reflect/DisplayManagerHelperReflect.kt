package net.refy.android.g8x.widemode.reflect

import android.content.Context
import tech.onsen.reflect.Reflect

class DisplayManagerHelperReflect(context: Context) : Reflect("com.lge.display.DisplayManagerHelper") {
    override val value = ctor(Context::class.java)(context)

    val getCoverDisplayId by virtual<Int>()
    val getCoverDisplayState by virtual<Int>()
    val keepCoverOnWhenFlipped by virtual<Unit>(Boolean::class.java)

    fun isCoverEnabled() = getCoverDisplayState() == 3
}