package com.alperenbabagil.simpleanimationpopuplibrary

import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable

class DefaultViewCreator {
    fun arrangeDialog(dialogView : View,
                      sapFragment : SapFragment,
                      titleStr: String? = null,
                      msgStr: String? = null,
                      animRes: Int? = null,
                      titleColorRes: Int=R.color.colorPrimary,
                      autoStartAnimation: Boolean = true,
                      loopAnimation: Boolean = false,
                      positiveButtonStrRes:Int?=null,
                      positiveButtonStr:String?=null,
                      positiveButtonClick:(() -> Unit)?=null,
                      negativeButtonStrRes:Int?=null,
                      negativeButtonStr:String?=null,
                      negativeButtonClick: (() -> Unit)?=null
                   ) {

        val animationView = dialogView.findViewById<LottieAnimationView>(R.id.sapAnimationView)

        animRes?.let {
            animationView?.setAnimation(it)
        } ?: kotlin.run {
            // loading default animations
            animationView?.setAnimation(R.raw.sap_error_anim)
        }

        loopAnimation.let {
            if (it) animationView?.repeatCount = LottieDrawable.INFINITE
            else animationView?.repeatCount = 0
        }

        if (!autoStartAnimation) animationView?.pauseAnimation()

        val titleTv = dialogView.findViewById<TextView>(R.id.sapTitleTv)
        val messageTv = dialogView.findViewById<TextView>(R.id.sapMessageTv)
        titleStr?.let {
            titleTv.text = it
        } ?: run{
            titleTv.visibility=View.GONE
        }

        // to show title with parametrized text color
        titleColorRes.let {
            titleTv.setTextColor(it)
            val color = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                 dialogView.context.getColor(titleColorRes)
            else  dialogView.context.resources.getColor(titleColorRes)
            titleTv.setTextColor(color)
        }

        msgStr?.takeIf { it.isNotEmpty() }?.let {
            messageTv.text = it
        } ?: kotlin.run {
            messageTv.visibility = View.GONE
        }

        positiveButtonStr?.let { btnStr->
            positiveButtonClick?.let {
                addButton(dialogView,sapFragment,btnStr,it,R.id.sapButtonPositive)
            } ?:addButton(dialogView,sapFragment,btnStr,{},R.id.sapButtonPositive)
        } ?: positiveButtonStrRes?.let {btnStrRes->
            positiveButtonClick?.let {
                addButton(dialogView,sapFragment,dialogView.context.getString(btnStrRes),
                    it,R.id.sapButtonPositive)
            } ?: addButton(dialogView,sapFragment,dialogView.context.getString(btnStrRes),{}
                ,R.id.sapButtonPositive)
        }

        negativeButtonStr?.let { btnStr->
            negativeButtonClick?.let {
                addButton(dialogView,sapFragment,btnStr,it
                    ,R.id.sapButtonNegative)
            } ?:addButton(dialogView,sapFragment,btnStr,{}
                ,R.id.sapButtonNegative)
        } ?: negativeButtonStrRes?.let {btnStrRes->
            negativeButtonClick?.let {
                addButton(dialogView,sapFragment,dialogView.context.getString(btnStrRes),it
                    ,R.id.sapButtonNegative)
            } ?: addButton(dialogView,sapFragment,dialogView.context.getString(btnStrRes),{}
                ,R.id.sapButtonNegative)
        }
    }

    private fun addButton(dialogView : View,
                  sapFragment: SapFragment,
                  buttonText : String,
                  clickEvent : () -> Unit = {},
                  resId:Int){
        dialogView.findViewById<Button>(resId).apply {
            visibility=View.VISIBLE
            text=buttonText
            setOnClickListener{
                sapFragment.removeCurrentDialog()
                clickEvent.invoke()
            }
        }
    }

}