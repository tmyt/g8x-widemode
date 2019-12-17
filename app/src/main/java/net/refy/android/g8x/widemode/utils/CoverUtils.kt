package net.refy.android.g8x.widemode.utils

class CoverUtils {

    private val displayManagerExUtils = DisplayManagerExUtils()

    fun isCoverEnabled(): Boolean {
        return displayManagerExUtils.getCoverDisplayState() == 3
    }
}