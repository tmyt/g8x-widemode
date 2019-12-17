package net.refy.android.g8x.widemode.utils

import android.content.Context

class DisplayHelperUtils( context: Context) {
    private val className = "com.lge.display.DisplayManagerHelper"

    private val cDisplayManagerHelper = Class.forName(className)
    private val displayManagerHelper = cDisplayManagerHelper
        .getDeclaredConstructor(Context::class.java).newInstance(context)
    private val mGetCoverDisplayId = cDisplayManagerHelper.getDeclaredMethod("getCoverDisplayId")

    fun getCoverDisplayId(): Int {
        return mGetCoverDisplayId.invoke(displayManagerHelper) as Int
    }
}