package com.alperenbabagil.simpleanimationpopuplibrary

import android.app.Activity
import android.os.Build


fun SapActivity.showLoadingDialog(
    animRes: Int = R.raw.sap_loading_anim,
    isCancellable: Boolean = false,
    sapCancelEvent: () -> Unit = {}
) {
    val activity = this as Activity
    val sapActivity = this as SapActivity
    sapActivity.currentDialog?.dismiss()
    sapActivity.currentDialog = SapDialog(activity).also { dialog ->
        dialog.isOnlyAnimation = true
        dialog.animResource = animRes
        dialog.isCancellable = isCancellable
        dialog.addCancelEvent(sapCancelEvent)
    }.build().show()
}

fun SapActivity.showDefaultDialog(
    titleRes: Int? = null,
    titleStr: String? = null,
    msgStr: String? = null,
    msgRes: Int? = null,
    animRes: Int? = null,
    titleColorRes: Int?,
    isCancellable: Boolean = false,
    isFullScreen: Boolean = false,
    autoStartAnimation: Boolean = true,
    loopAnimation: Boolean = false,
    positiveButton: Pair<String, (() -> Unit)?>?,
    negativeButton: Pair<String, (() -> Unit)?>?
) {
    val activity = this as Activity
    val sapActivity = this as SapActivity
    sapActivity.currentDialog?.dismiss()
    sapActivity.currentDialog = SapDialog(activity).also { dialog ->
        dialog.animResource = animRes
        dialog.isCancellable = isCancellable
        dialog.loopAnimation = loopAnimation
        dialog.autoStartAnimation = autoStartAnimation
        dialog.isFullScreen = isFullScreen
        titleColorRes?.let {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                dialog.titleTextColor = activity.getColor(it)
            else dialog.titleTextColor = activity.resources.getColor(it)
        }
        dialog.messageText = msgStr ?: let {
            msgRes?.let {
                activity.getString(it)
            }
        }
        dialog.titleText = titleStr ?: let {
            titleRes?.let {
                activity.getString(it)
            }
        }
        positiveButton?.let {
            dialog.addPositiveButton(it.first, it.second ?: {})
        }
        negativeButton?.let {
            dialog.addNegativeButton(it.first, it.second ?: {})
        }
    }.build().show()
}


fun SapActivity.showWarningDialog(
    titleRes: Int? = SapDialog.WARNING_RES_ID,
    titleStr: String? = null,
    warningStr: String? = null,
    warningRes: Int?=null,
    animRes: Int = R.raw.sap_warning_anim,
    titleColorRes: Int = SapDialog.WARNING_TITLE_COLOR_RES,
    isCancellable: Boolean = true,
    isFullScreen: Boolean = false,
    autoStartAnimation: Boolean = true,
    loopAnimation: Boolean = false,
    positiveButton: Pair<String, (() -> Unit)?>?=null,
    negativeButton: Pair<String, (() -> Unit)?>?=null
) = showDefaultDialog(
    titleRes,
    titleStr,
    warningStr,
    warningRes,
    animRes,
    titleColorRes,
    isCancellable,
    isFullScreen,
    autoStartAnimation,
    loopAnimation,
    positiveButton,
    negativeButton
)


fun SapActivity.showErrorDialog(
    titleRes: Int? = SapDialog.ERROR_RES_ID,
    titleStr: String? = null,
    errorStr: String? = null,
    errorRes: Int? = null,
    animRes: Int = R.raw.sap_error_anim,
    titleColorRes: Int = SapDialog.ERROR_TITLE_COLOR_RES,
    isCancellable: Boolean = false,
    isFullScreen: Boolean = false,
    autoStartAnimation: Boolean = true,
    loopAnimation: Boolean = false,
    positiveButton: Pair<String, (() -> Unit)?>?=null,
    negativeButton: Pair<String, (() -> Unit)?>?=null
) = showDefaultDialog(
    titleRes,
    titleStr,
    errorStr,
    errorRes,
    animRes,
    titleColorRes,
    isCancellable,
    isFullScreen,
    autoStartAnimation,
    loopAnimation,
    positiveButton,
    negativeButton
)

fun SapActivity.showInfoDialog(
    titleRes: Int? = SapDialog.INFO_RES_ID,
    titleStr: String? = null,
    infoStr: String? = null,
    infoRes: Int? = null,
    animRes: Int = R.raw.sap_info_anim,
    titleColorRes: Int = SapDialog.INFO_TITLE_COLOR_RES,
    isCancellable: Boolean = false,
    isFullScreen: Boolean = false,
    autoStartAnimation: Boolean = true,
    loopAnimation: Boolean = false,
    positiveButton: Pair<String, (() -> Unit)?>?=null,
    negativeButton: Pair<String, (() -> Unit)?>?=null
) = showDefaultDialog(
    titleRes,
    titleStr,
    infoStr,
    infoRes,
    animRes,
    titleColorRes,
    isCancellable,
    isFullScreen,
    autoStartAnimation,
    loopAnimation,
    positiveButton,
    negativeButton
)


