package net.refy.android.g8x.widemode.reflect

import android.annotation.SuppressLint
import android.content.Context
import tech.onsen.reflect.Reflect

class StatusBarManagerReflect(context: Context) : Reflect("android.app.StatusBarManager") {
    @SuppressLint("WrongConstant")
    override val value = context.getSystemService("statusbar")
    val collapsePanels by virtual<Unit>()
}