package com.alperenbabagil.simpleanimationpopuplibrary

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable

fun SapFragment.addDialog(view: View) {
    ((this as? Fragment)?.view as? ViewGroup)?.let {
        currentDialogView = view
        it.addView(view, it.layoutParams)
    }
}

fun SapFragment.removeCurrentDialog() {
    ((this as? Fragment)?.view as? ViewGroup)?.let { rootView ->
        currentDialogView?.let {
            try {
                rootView.removeView(it)
            } catch (e: Exception) {
                e.printStackTrace()
                //most probably view does not exists
            }
        }
    }
}

fun SapFragment.showLoadingDialog(
    animRes: Int = R.raw.sap_loading_anim,
    isCancellable: Boolean = false,
    sapCancelEvent: () -> Unit = {}
) {
    ((this as? Fragment)?.activity)?.let {_->
        (this.view as? ViewGroup)?.let { viewGroup ->
            this.layoutInflater.inflate(R.layout.fragment_loading_dialog_layout, viewGroup, false)
                .let {
                    it.findViewById<LottieAnimationView>(R.id.sapAnimationView).apply {
                        setAnimation(animRes)
                        repeatCount= LottieDrawable.INFINITE
                    }
                    it.setOnClickListener {
                        if (isCancellable) {
                            this.removeCurrentDialog()
                            sapCancelEvent.invoke()
                        }
                    }
                    addDialog(it)
                }
        }
    }
}

fun SapFragment.showDefaultDialog(
    titleRes: Int? = null,
    titleStr: String? = null,
    msgStr: String? = null,
    msgRes: Int? = null,
    animRes: Int? = null,
    titleColorRes: Int = R.color.colorPrimary,
    isCancellable: Boolean = false,
    autoStartAnimation: Boolean = true,
    loopAnimation: Boolean = false,
    positiveButtonStrRes: Int? = null,
    positiveButtonStr: String? = null,
    positiveButtonClick: (() -> Unit)? = null,
    negativeButtonStrRes: Int? = null,
    negativeButtonStr: String? = null,
    negativeButtonClick: (() -> Unit)? = null,
    cancelEvent :  (() -> Unit) ={}
) {
    removeCurrentDialog()
    ((this as Fragment).activity)?.let {activity->
        (this.view as? ViewGroup)?.let { viewGroup ->
            this.layoutInflater.inflate(R.layout.fragment_default_dialog_layout, viewGroup, false)
                .let {
                    it.setOnClickListener {
                        if(isCancellable){
                            cancelEvent.invoke()
                            removeCurrentDialog()
                        }
                    }
                    DefaultViewCreator().arrangeDialog(dialogView = it,
                        sapFragment = this,
                        msgStr = msgStr ?: let {
                            msgRes?.let {
                                activity.getString(it)
                            }
                        },
                        titleStr = titleStr ?: let {
                            titleRes?.let {
                                activity.getString(it)
                            }
                        },
                        positiveButtonStr = positiveButtonStr,
                        positiveButtonStrRes = positiveButtonStrRes,
                        positiveButtonClick = positiveButtonClick,
                        negativeButtonStr = negativeButtonStr,
                        negativeButtonStrRes = negativeButtonStrRes,
                        negativeButtonClick = negativeButtonClick,
                        loopAnimation = loopAnimation,
                        autoStartAnimation = autoStartAnimation,
                        titleColorRes = titleColorRes,
                        animRes = animRes
                        )
                    addDialog(it)
                }
        }
    }
}

fun SapFragment.showErrorDialog(
    titleRes: Int? = SapDialog.ERROR_RES_ID,
    titleStr: String? = null,
    errorStr: String? = null,
    errorRes: Int? = null,
    animRes: Int? = R.raw.sap_error_anim,
    titleColorRes: Int = SapDialog.ERROR_TITLE_COLOR_RES,
    isCancellable: Boolean = false,
    autoStartAnimation: Boolean = true,
    loopAnimation: Boolean = false,
    positiveButtonStrRes: Int? = null,
    positiveButtonStr: String? = null,
    positiveButtonClick: (() -> Unit)? = null,
    negativeButtonStrRes: Int? = null,
    negativeButtonStr: String? = null,
    negativeButtonClick: (() -> Unit)? = null,
    cancelEvent :  (() -> Unit) ={}
) {
    removeCurrentDialog()
    showDefaultDialog(titleRes,
        titleStr,
        errorStr,
        errorRes,
        animRes,
        titleColorRes,
        isCancellable,
        autoStartAnimation,
        loopAnimation,
        positiveButtonStrRes,
        positiveButtonStr,
        positiveButtonClick,
        negativeButtonStrRes,
        negativeButtonStr,
        negativeButtonClick,
        cancelEvent
        )
}

fun SapFragment.showWarningDialog(
    titleRes: Int? = SapDialog.WARNING_RES_ID,
    titleStr: String? = null,
    warningStr: String? = null,
    warningRes: Int? = null,
    animRes: Int? = R.raw.sap_warning_anim,
    titleColorRes: Int = SapDialog.WARNING_TITLE_COLOR_RES,
    isCancellable: Boolean = true,
    autoStartAnimation: Boolean = true,
    loopAnimation: Boolean = false,
    positiveButtonStrRes: Int? = null,
    positiveButtonStr: String? = null,
    positiveButtonClick: (() -> Unit)? = null,
    negativeButtonStrRes: Int? = null,
    negativeButtonStr: String? = null,
    negativeButtonClick: (() -> Unit)? = null,
    cancelEvent :  (() -> Unit) ={}
) {
    removeCurrentDialog()
    showDefaultDialog(titleRes,
        titleStr,
        warningStr,
        warningRes,
        animRes,
        titleColorRes,
        isCancellable,
        autoStartAnimation,
        loopAnimation,
        positiveButtonStrRes,
        positiveButtonStr,
        positiveButtonClick,
        negativeButtonStrRes,
        negativeButtonStr,
        negativeButtonClick,
        cancelEvent
    )
}

fun SapFragment.showInfoDialog(
    titleRes: Int? = SapDialog.INFO_RES_ID,
    titleStr: String? = null,
    infoStr: String? = null,
    infoRes: Int? = null,
    animRes: Int? = R.raw.sap_info_anim,
    titleColorRes: Int = SapDialog.INFO_TITLE_COLOR_RES,
    isCancellable: Boolean = true,
    autoStartAnimation: Boolean = true,
    loopAnimation: Boolean = false,
    positiveButtonStrRes: Int? = null,
    positiveButtonStr: String? = null,
    positiveButtonClick: (() -> Unit)? = null,
    negativeButtonStrRes: Int? = null,
    negativeButtonStr: String? = null,
    negativeButtonClick: (() -> Unit)? = null,
    cancelEvent :  (() -> Unit) ={}
) {
    removeCurrentDialog()
    showDefaultDialog(titleRes,
        titleStr,
        infoStr,
        infoRes,
        animRes,
        titleColorRes,
        isCancellable,
        autoStartAnimation,
        loopAnimation,
        positiveButtonStrRes,
        positiveButtonStr,
        positiveButtonClick,
        negativeButtonStrRes,
        negativeButtonStr,
        negativeButtonClick,
        cancelEvent
    )
}

