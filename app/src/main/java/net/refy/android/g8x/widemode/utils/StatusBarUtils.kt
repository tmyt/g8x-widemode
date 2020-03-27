package net.refy.android.g8x.widemode.utils

import android.annotation.SuppressLint
import android.content.Context
import tech.onsen.reflect.Reflect

@SuppressLint("WrongConstant")
class StatusBarUtils(context: Context) : Reflect("android.app.StatusBarManager") {
    override val value = context.getSystemService("statusbar")
    val collapsePanels by virtual<Unit>()
}