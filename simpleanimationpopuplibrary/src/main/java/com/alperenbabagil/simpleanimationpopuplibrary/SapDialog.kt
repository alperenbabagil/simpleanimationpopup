package com.alperenbabagil.simpleanimationpopuplibrary

import android.app.Activity
import android.app.Dialog
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable.INFINITE


class SapDialog(private val activity: Activity)  {

    private lateinit var dialog : Dialog
    private var positiveButtonText: String?=null
    private var negativeButtonText: String?=null
    var titleText: String?=null
    var messageText: String?=null
    var isCancellable=false
    var isOnlyAnimation=false
    var animResource:Int?=null
    var loopAnimation:Boolean?=null
    var autoStartAnimation=true
    var titleTextColor:Int?=null
    var isFullScreen=false
    private var positiveButtonClickEvent : () -> Unit = {}
    private var negativeButtonClickEvent : () -> Unit = {}
    private var sapDismissEvent : () -> Unit = {}
    private var sapCancelEvent : () -> Unit = {}
    private var positiveButtonVisible =false
    private var negativeButtonVisible =false
    private var animationView : LottieAnimationView?=null

    fun addPositiveButton(buttonText : String,clickEvent : () -> Unit = {}){
        positiveButtonVisible=true
        positiveButtonText=buttonText
        positiveButtonClickEvent=clickEvent
    }

    fun addNegativeButton(buttonText : String,clickEvent : () -> Unit){
        negativeButtonVisible=true
        negativeButtonText=buttonText
        negativeButtonClickEvent=clickEvent
    }

    fun addDismissEvent(dismissEvent : () -> Unit){
        sapDismissEvent=dismissEvent
    }

    fun addCancelEvent(cancelEvent : () -> Unit){
        sapCancelEvent=cancelEvent
    }

    fun build() : SapDialog{
        dialog = AlertDialog.Builder(activity)
            .setView(createView())
            .setCancelable(isCancellable)
            .setOnDismissListener { sapDismissEvent.invoke() }
            .setOnCancelListener { sapCancelEvent.invoke() }
            .create().apply {
                window?.setBackgroundDrawableResource(android.R.color.transparent)
            }
        return this
    }

    // to keep reference and show dialog with one method
    fun show() : Dialog{
        // a hacky solution from stackoverflow
        // making not focusable to not trigger system ui
        if(isFullScreen){
            dialog.window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        }

        dialog.show()

        // adding fullscreen flags to dialog
        if(isFullScreen){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                dialog.window?.decorView?.systemUiVisibility =
                    (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            }
        }

        // making focusable again to interact with user
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)

        return dialog
    }

    fun hide(){
        dialog.hide()
    }

    fun cancel(){
        dialog.cancel()
    }

    fun dismiss(){
        dialog.dismiss()
    }

    fun startAnimation(){
        animationView?.playAnimation()
    }

    private fun createView() : View{
        val inflater: LayoutInflater = activity.layoutInflater

        // if isOnlyAnimation set to true 'simple_only_animation_popup.xml' will be loaded. Else 'simple_animation_popup_layout' will be loaded
        val res=if(isOnlyAnimation) R.layout.simple_only_animation_popup else R.layout.simple_animation_popup_layout
        val dialogView = inflater.inflate(res,null,false)
        animationView = dialogView.findViewById(R.id.sapAnimationView)

        animResource?.let {
            animationView?.setAnimation(it)
        }?: kotlin.run {
            // loading default animations
            if(isOnlyAnimation){
                animationView?.setAnimation(R.raw.sap_loading_anim)
            }
            else animationView?.setAnimation(R.raw.sap_error_anim)
        }

        loopAnimation?.let {
            if(it) animationView?.repeatCount= INFINITE
            else animationView?.repeatCount=0
        } ?: kotlin.run {
            //setting default animation loop state.
            if(isOnlyAnimation) animationView?.repeatCount= INFINITE
            else animationView?.repeatCount=0
        }

        if(!autoStartAnimation) animationView?.pauseAnimation()

        // shows with a dialog with title, animation view and action buttons
        if(!isOnlyAnimation){
            val titleTv=dialogView.findViewById<TextView>(R.id.sapTitleTv)
            val messageTv=dialogView.findViewById<TextView>(R.id.sapMessageTv)
            titleTv.text=titleText

            // to show title with parametrized text color
            titleTextColor?.let {
                titleTv.setTextColor(it)
            }

            messageText?.takeIf { it.isNotEmpty() }?.let {
                messageTv.text=it
            } ?: kotlin.run {
                messageTv.visibility=View.GONE
            }

            // arranging positive button and set visible
            if(positiveButtonVisible){
                dialogView.findViewById<Button>(R.id.sapButtonPositive).apply {
                    text=positiveButtonText
                    setOnClickListener {
                        dismiss()
                        positiveButtonClickEvent.invoke()
                    }
                    visibility=View.VISIBLE
                }
            }

            // arranging negative button and set visible
            if(negativeButtonVisible){
                dialogView.findViewById<Button>(R.id.sapButtonNegative).apply {
                    text=negativeButtonText
                    setOnClickListener {
                        cancel()
                        negativeButtonClickEvent.invoke()
                    }
                    visibility=View.VISIBLE
                }
            }
        }

        return dialogView
    }

    companion object{
        var ERROR_RES_ID=R.string.error
        var WARNING_RES_ID=R.string.warning
        var INFO_RES_ID=R.string.info
        var ERROR_TITLE_COLOR_RES=android.R.color.holo_red_dark
        var WARNING_TITLE_COLOR_RES=android.R.color.holo_orange_dark
        var INFO_TITLE_COLOR_RES=android.R.color.holo_blue_dark
        var LOADING_RES_ID=R.string.loading
    }
}