package com.alperenbabagil.simpleanimationpopup

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alperenbabagil.simpleanimationpopuplibrary.SapDialog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            "2 buttons cancellable dialog cancel listener"
        )

        val adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.adapter=adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            when (position){
                0->{
                    SapDialog(this).apply {
                        titleText="Lorem ipsum"
                        messageText="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod"
                        addPositiveButton("ok"){
                            Toast.makeText(this@MainActivity,"positive button clicked",Toast.LENGTH_SHORT).show()
                        }
                    }.build().show()
                }

                1->{
                    SapDialog(this).apply {
                        titleText="Lorem ipsum"
                        messageText="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod"
                        addPositiveButton("ok"){
                            Toast.makeText(this@MainActivity,"positive button clicked",Toast.LENGTH_SHORT).show()
                        }
                        addNegativeButton("cancel"){
                            Toast.makeText(this@MainActivity,"negative button clicked",Toast.LENGTH_SHORT).show()
                        }
                    }.build().show()
                }

                2->{
                    SapDialog(this).apply {
                        titleText="Lorem ipsum"
                        messageText="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod"
                        isCancellable=true
                    }.build().show()
                }

                3->{
                    SapDialog(this).apply {
                        titleText="Lorem ipsum"
                        messageText="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod"
                        addPositiveButton("ok"){
                            Toast.makeText(this@MainActivity,"positive button clicked",Toast.LENGTH_SHORT).show()
                        }
                        animResource=R.raw.info
                    }.build().show()
                }
                4->{
                    SapDialog(this).apply {
                        isOnlyAnimation=true
                    }.build().show()
                }
                5->{
                    SapDialog(this).apply {
                        isOnlyAnimation=true
                        animResource=R.raw.loading
                    }.build().show()
                }
                6->{
                    SapDialog(this).apply {
                        isOnlyAnimation=true
                        isCancellable=true
                    }.build().show()
                }
                7->{
                    SapDialog(this).apply {
                        isOnlyAnimation=true
                        isCancellable=true
                        addDismissEvent {
                            Toast.makeText(this@MainActivity,"dialog dismissed",Toast.LENGTH_SHORT).show()
                        }
                    }.build().show()
                }
                8->{
                    SapDialog(this).apply {
                        titleText="Lorem ipsum"
                        isCancellable=true
                    }.build().show()
                }
                9->{
                    SapDialog(this).apply {
                        titleText="Lorem ipsum"
                        isCancellable=true
                        titleTextColor=R.color.colorAccent
                    }.build().show()
                }
                10->{
                    SapDialog(this).apply {
                        titleText="Lorem ipsum"
                        messageText="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod"
                        addPositiveButton("ok"){
                            Toast.makeText(this@MainActivity,"positive button clicked",Toast.LENGTH_SHORT).show()
                        }
                        animResource=R.raw.info
                        loopAnimation=true
                    }.build().show()
                }
                11->{
                    SapDialog(this).apply {
                        titleText="Lorem ipsum"
                        messageText="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod"
                        addPositiveButton("ok"){
                            Toast.makeText(this@MainActivity,"positive button clicked",Toast.LENGTH_SHORT).show()
                        }
                        addNegativeButton("cancel"){
                            Toast.makeText(this@MainActivity,"negative button clicked",Toast.LENGTH_SHORT).show()
                        }
                        addCancelEvent {
                            Toast.makeText(this@MainActivity,"dialog cancelled",Toast.LENGTH_SHORT).show()
                        }
                        animResource=R.raw.info
                        loopAnimation=true
                        isCancellable=true
                    }.build().show()
                }
            }
        }
    }
}
