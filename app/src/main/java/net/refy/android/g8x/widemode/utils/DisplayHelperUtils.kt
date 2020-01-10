package net.refy.android.g8x.widemode.utils

import android.content.Context
import net.refy.android.reflect.Reflect

class DisplayHelperUtils(context: Context) : Reflect() {
    override val type = Class.forName("com.lge.display.DisplayManagerHelper")
    override val value = ctor(Context::class.java)(context)
    val getCoverDisplayId by virtual<Int>()
}