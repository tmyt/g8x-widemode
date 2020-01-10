package net.refy.android.g8x.widemode.utils

import android.annotation.SuppressLint
import android.content.Context
import net.refy.android.reflect.Reflect

@SuppressLint("WrongConstant")
class StatusBarUtils(context: Context) : Reflect() {
    override val type = Class.forName("android.app.StatusBarManager")
    override val value = context.getSystemService("statusbar")
    val collapsePanels by virtual<Unit>()
}