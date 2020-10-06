package com.alperenbabagil.simpleanimationpopup

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alperenbabagil.simpleanimationpopuplibrary.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), SapActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // you can safely call it in the onCreate()
        showLoadingDialog(isCancellable = true) {

        }

        val values = arrayOf(
            "Default Alert Dialog with one button",
            "Default Alert Dialog with two buttons",
            "Default Alert Dialog no buttons but cancellable",
            "Default Alert Dialog with custom animation",
            "Dialog with only animation (not cancellable)",
            "Dialog with only custom animation (not cancellable)",
            "Cancellable only animation dialog",
            "Dialog with dismiss listener",
            "Dialog with no message",
            "Dialog with custom title color",
            "Default Alert Dialog with custom animation loop",
            "2 buttons cancellable dialog cancel listener",
            "Show dialog maintaining fullscreen mode after 2 secs",
            "Show loading dialog with extension function",
            "Show warning dialog with extension function",
            "Show error dialog with extension function",
            "Show info dialog with extension function",
            "Dialog fragment"
        )

        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1, android.R.id.text1, values
        );

        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            when (position) {
                0 -> {
                    SapDialog(this).apply {
                        titleText = "Lorem ipsum"
                        messageText =
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod"
                        addPositiveButton("ok") {
                            Toast.makeText(
                                this@MainActivity,
                                "positive button clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }.build().show()
                }

                1 -> {
                    SapDialog(this).apply {
                        titleText = "Lorem ipsum"
                        messageText =
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod"
                        addPositiveButton("ok") {
                            Toast.makeText(
                                this@MainActivity,
                                "positive button clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        addNegativeButton("cancel") {
                            Toast.makeText(
                                this@MainActivity,
                                "negative button clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }.build().show()
                }

                2 -> {
                    SapDialog(this).apply {
                        titleText = "Lorem ipsum"
                        messageText =
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod"
                        isCancellable = true
                    }.build().show()
                }

                3 -> {
                    SapDialog(this).apply {
                        titleText = "Lorem ipsum"
                        messageText =
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod"
                        addPositiveButton("ok") {
                            Toast.makeText(
                                this@MainActivity,
                                "positive button clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        animResource = R.raw.info
                    }.build().show()
                }
                4 -> {
                    SapDialog(this).apply {
                        isOnlyAnimation = true
                    }.build().show()
                }
                5 -> {
                    SapDialog(this).apply {
                        isOnlyAnimation = true
                        animResource = R.raw.loading
                    }.build().show()
                }
                6 -> {
                    SapDialog(this).apply {
                        isOnlyAnimation = true
                        isCancellable = true
                    }.build().show()
                }
                7 -> {
                    SapDialog(this).apply {
                        isOnlyAnimation = true
                        isCancellable = true
                        addDismissEvent {
                            Toast.makeText(
                                this@MainActivity,
                                "dialog dismissed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }.build().show()
                }
                8 -> {
                    SapDialog(this).apply {
                        titleText = "Lorem ipsum"
                        isCancellable = true
                    }.build().show()
                }
                9 -> {
                    SapDialog(this).apply {
                        titleText = "Lorem ipsum"
                        isCancellable = true
                        titleTextColor = R.color.colorAccent
                    }.build().show()
                }
                10 -> {
                    SapDialog(this).apply {
                        titleText = "Lorem ipsum"
                        messageText =
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod"
                        addPositiveButton("ok") {
                            Toast.makeText(
                                this@MainActivity,
                                "positive button clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        animResource = R.raw.info
                        loopAnimation = true
                    }.build().show()
                }
                11 -> {
                    SapDialog(this).apply {
                        titleText = "Lorem ipsum"
                        messageText =
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod"
                        addPositiveButton("ok") {
                            Toast.makeText(
                                this@MainActivity,
                                "positive button clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        addNegativeButton("cancel") {
                            Toast.makeText(
                                this@MainActivity,
                                "negative button clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        addCancelEvent {
                            Toast.makeText(
                                this@MainActivity,
                                "dialog cancelled",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        animResource = R.raw.info
                        loopAnimation = true
                        isCancellable = true
                    }.build().show()
                }
                12 -> {
                    enterFullScreen()
                    Handler().postDelayed({
                        runOnUiThread {
                            SapDialog(this).apply {
                                titleText = "Lorem ipsum"
                                messageText =
                                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod"
                                addPositiveButton("ok") {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "positive button clicked",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                addNegativeButton("cancel") {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "negative button clicked",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                addCancelEvent {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "dialog cancelled",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                animResource = R.raw.info
                                loopAnimation = true
                                isCancellable = true
                                isFullScreen = true
                            }.build().show()
                        }
                    }, 2000)
                }
                13 -> {
                    showLoadingDialog(isCancellable = true){
                        //dialog is cancelled
                    }
                }
                14 -> {
                    showWarningDialog(
                        warningStr = "This is a warning string",
                        positiveButtonStr =  "Wow, I'm warned"
                    )
                }
                15 -> {
                    showErrorDialog(titleRes = R.string.dangerous_error,
                        positiveButtonStrRes = R.string.ok,
                        positiveButtonClick = {
                            // run error negative button clicked
                        },
                        negativeButtonStr = "whatever",
                        negativeButtonClick = {
                            // run error negative button clicked
                        }
                    )
                }
                16 -> {
                    showInfoDialog(infoStr = "you are informed",isCancellable = true)
                }
                17->{
                    startActivity(Intent(this,FragmentActivity::class.java))
                }
            }
        }
    }

    private fun enterFullScreen() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)

        supportActionBar?.hide()
    }

    override var currentDialog: Dialog? = null
}
