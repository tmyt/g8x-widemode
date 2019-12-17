package net.refy.android.g8x.widemode.utils

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("WrongConstant")
class StatusBarUtils(context: Context){
    private val statusbarService = context.getSystemService("statusbar")
    private val cStatusbarManager = Class.forName( "android.app.StatusBarManager" )
    private val mCollapsePanels = cStatusbarManager.getMethod("collapsePanels")

    fun collapsePanels(){
        mCollapsePanels.invoke(statusbarService)
    }
}