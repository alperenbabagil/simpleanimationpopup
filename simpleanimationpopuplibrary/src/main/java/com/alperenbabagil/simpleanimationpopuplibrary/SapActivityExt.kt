package com.alperenbabagil.simpleanimationpopuplibrary

import android.app.Activity
import android.app.Dialog
import android.os.Build
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle


fun SapActivity.showLoadingDialog(
    animRes: Int = R.raw.sap_loading_anim,
    isCancellable: Boolean = false,
    sapCancelEvent: () -> Unit = {}
) {
    val activity = this as ComponentActivity
    val sapActivity = this as SapActivity
    sapActivity.currentDialog?.dismiss()
    sapActivity.currentDialog = SapDialog(activity).also { dialog ->
        dialog.isOnlyAnimation = true
        dialog.animResource = animRes
        dialog.isCancellable = isCancellable
        dialog.addCancelEvent(sapCancelEvent)
    }.build().dialog
    if(!activity.lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)){
        Handler().post {
            sapActivity.currentDialog!!.show()
        }
    }
    else sapActivity.currentDialog!!.show()
}

fun SapActivity.showDefaultDialog(
    titleRes: Int? = null,
    titleStr: String? = null,
    msgStr: String? = null,
    msgRes: Int? = null,
    animRes: Int? = null,
    titleColorRes: Int=R.color.colorPrimary,
    isCancellable: Boolean = false,
    isFullScreen: Boolean = false,
    autoStartAnimation: Boolean = true,
    loopAnimation: Boolean = false,
    positiveButtonStrRes:Int?=null,
    positiveButtonStr:String?=null,
    positiveButtonClick:(() -> Unit)?=null,
    negativeButtonStrRes:Int?=null,
    negativeButtonStr:String?=null,
    negativeButtonClick:(() -> Unit)?=null
) {
    val activity = this as ComponentActivity
    val sapActivity = this as SapActivity
    sapActivity.currentDialog?.dismiss()
    sapActivity.currentDialog = SapDialog(activity).also { dialog ->
        dialog.animResource = animRes
        dialog.isCancellable = isCancellable
        dialog.loopAnimation = loopAnimation
        dialog.autoStartAnimation = autoStartAnimation
        dialog.isFullScreen = isFullScreen
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            dialog.titleTextColor = activity.getColor(titleColorRes)
        else dialog.titleTextColor = activity.resources.getColor(titleColorRes)
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
        positiveButtonStr?.let { btnStr->
            positiveButtonClick?.let {
                dialog.addPositiveButton(btnStr,it)
            } ?: dialog.addPositiveButton(btnStr){}
        } ?: positiveButtonStrRes?.let {btnStrRes->
            positiveButtonClick?.let {
                dialog.addPositiveButton(activity.getString(btnStrRes),it)
            } ?: dialog.addPositiveButton(activity.getString(btnStrRes)){}
        }

        negativeButtonStr?.let { btnStr->
            negativeButtonClick?.let {
                dialog.addNegativeButton(btnStr,it)
            } ?: dialog.addNegativeButton(btnStr){}
        } ?: negativeButtonStrRes?.let {btnStrRes->
            negativeButtonClick?.let {
                dialog.addNegativeButton(activity.getString(btnStrRes),it)
            } ?: dialog.addNegativeButton(activity.getString(btnStrRes)){}
        }

    }.build().dialog
    if(!activity.lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)){
        Handler().post {
            sapActivity.currentDialog!!.show()
        }
    }
    else sapActivity.currentDialog!!.show()
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
    positiveButtonStrRes:Int?=null,
    positiveButtonStr:String?=null,
    positiveButtonClick:(() -> Unit)?=null,
    negativeButtonStrRes:Int?=null,
    negativeButtonStr:String?=null,
    negativeButtonClick:(() -> Unit)?=null
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
    positiveButtonStrRes,
    positiveButtonStr,
    positiveButtonClick,
    negativeButtonStrRes,
    negativeButtonStr,
    negativeButtonClick
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
    positiveButtonStrRes:Int?=null,
    positiveButtonStr:String?=null,
    positiveButtonClick:(() -> Unit)?=null,
    negativeButtonStrRes:Int?=null,
    negativeButtonStr:String?=null,
    negativeButtonClick:(() -> Unit)?=null
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
    positiveButtonStrRes,
    positiveButtonStr,
    positiveButtonClick,
    negativeButtonStrRes,
    negativeButtonStr,
    negativeButtonClick
)

fun SapActivity.showInfoDialog(
    titleRes: Int? = SapDialog.INFO_RES_ID,
    titleStr: String? = null,
    infoStr: String? = null,
    infoRes: Int? = null,
    animRes: Int = R.raw.sap_info_anim,
    titleColorRes: Int = SapDialog.INFO_TITLE_COLOR_RES,
    isCancellable: Boolean = true,
    isFullScreen: Boolean = false,
    autoStartAnimation: Boolean = true,
    loopAnimation: Boolean = false,
    positiveButtonStrRes:Int?=null,
    positiveButtonStr:String?=null,
    positiveButtonClick:(() -> Unit)?=null,
    negativeButtonStrRes:Int?=null,
    negativeButtonStr:String?=null,
    negativeButtonClick:(() -> Unit)?=null
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
    positiveButtonStrRes,
    positiveButtonStr,
    positiveButtonClick,
    negativeButtonStrRes,
    negativeButtonStr,
    negativeButtonClick
)


