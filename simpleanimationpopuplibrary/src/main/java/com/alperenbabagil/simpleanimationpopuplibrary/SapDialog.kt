package com.alperenbabagil.simpleanimationpopuplibrary

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
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
    private var positiveButtonClickEvent : () -> Unit = {}
    private var negativeButtonClickEvent : () -> Unit = {}
    private var sapDismissEvent : () -> Unit = {}
    private var positiveButtonVisible =false
    private var negativeButtonVisible =false

    fun addPositiveButton(buttonText : String,clickEvent : () -> Unit){
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

    fun build() : SapDialog{
        dialog = AlertDialog.Builder(activity)
            .setView(createView())
            .setCancelable(isCancellable)
            .setOnDismissListener { sapDismissEvent.invoke() }
            .create().apply {
                window?.setBackgroundDrawableResource(android.R.color.transparent)
            }
        return this
    }

    // to keep reference and show dialog with one method
    fun show() : Dialog{
        dialog.show()
        return dialog
    }

    fun hide(){
        dialog.hide()
    }

    fun cancel(){
        dialog.cancel()
    }

    private fun createView() : View{
        val inflater: LayoutInflater = activity.layoutInflater

        // if isOnlyAnimation set to true 'simple_only_animation_popup.xml' will be loaded. Else 'simple_animation_popup_layout' will be loaded
        val res=if(isOnlyAnimation) R.layout.simple_only_animation_popup else R.layout.simple_animation_popup_layout
        val dialogView = inflater.inflate(res,null,false)
        val animationView = dialogView.findViewById<LottieAnimationView>(R.id.sapAnimationView)

        animResource?.let {
            animationView.setAnimation(it)
        }?: kotlin.run {
            // loading default animations
            if(isOnlyAnimation){
                animationView.setAnimation(R.raw.sap_loading_anim)
            }
            else animationView.setAnimation(R.raw.sap_error_anim)
        }

        loopAnimation?.let {
            if(it) animationView.repeatCount= INFINITE
            else animationView.repeatCount=0
        } ?: kotlin.run {
            //setting default animation loop state.
            if(isOnlyAnimation) animationView.repeatCount= INFINITE
            else animationView.repeatCount=0
        }

        if(!autoStartAnimation) animationView.pauseAnimation()

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
                        cancel()
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
}