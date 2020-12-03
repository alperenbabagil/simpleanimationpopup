package com.alperenbabagil.simpleanimationpopup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.alperenbabagil.simpleanimationpopuplibrary.*
import kotlinx.android.synthetic.main.activity_main.*

class DemoFragment : Fragment(),SapFragment{
    override var currentDialogView: View?=null
    override var onBackPressedCallback: OnBackPressedCallback?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.demo_fragment_layout,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
            "Do nothing",
            "Show loading dialog with extension function",
            "Show warning dialog with extension function",
            "Show error dialog with extension function",
            "Show info dialog with extension function",
            "Dialog loading fragment",
            "Dialog error fragment",
            "Dialog warning fragment",
            "Dialog info fragment"
        )

        val adapter = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_list_item_1, android.R.id.text1, values
        );

        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            when (position) {
                0 -> {
                    SapDialog(requireActivity()).apply {
                        titleText = "Lorem ipsum"
                        messageText =
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod"
                        addPositiveButton("ok") {
                            Toast.makeText(
                                requireContext(),
                                "positive button clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }.build().show()
                }

                1 -> {
                    SapDialog(requireActivity()).apply {
                        titleText = "Lorem ipsum"
                        messageText =
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod"
                        addPositiveButton("ok") {
                            Toast.makeText(
                                requireActivity(),
                                "positive button clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        addNegativeButton("cancel") {
                            Toast.makeText(
                                requireActivity(),
                                "negative button clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }.build().show()
                }

                2 -> {
                    SapDialog(requireActivity()).apply {
                        titleText = "Lorem ipsum"
                        messageText =
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod"
                        isCancellable = true
                    }.build().show()
                }

                3 -> {
                    SapDialog(requireActivity()).apply {
                        titleText = "Lorem ipsum"
                        messageText =
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod"
                        addPositiveButton("ok") {
                            Toast.makeText(
                                requireActivity(),
                                "positive button clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        animResource = R.raw.info
                    }.build().show()
                }
                4 -> {
                    SapDialog(requireActivity()).apply {
                        isOnlyAnimation = true
                    }.build().show()
                }
                5 -> {
                    SapDialog(requireActivity()).apply {
                        isOnlyAnimation = true
                        animResource = R.raw.loading
                    }.build().show()
                }
                6 -> {
                    SapDialog(requireActivity()).apply {
                        isOnlyAnimation = true
                        isCancellable = true
                    }.build().show()
                }
                7 -> {
                    SapDialog(requireActivity()).apply {
                        isOnlyAnimation = true
                        isCancellable = true
                        addDismissEvent {
                            Toast.makeText(
                                requireActivity(),
                                "dialog dismissed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }.build().show()
                }
                8 -> {
                    SapDialog(requireActivity()).apply {
                        titleText = "Lorem ipsum"
                        isCancellable = true
                    }.build().show()
                }
                9 -> {
                    SapDialog(requireActivity()).apply {
                        titleText = "Lorem ipsum"
                        isCancellable = true
                        titleTextColor = R.color.colorAccent
                    }.build().show()
                }
                10 -> {
                    SapDialog(requireActivity()).apply {
                        titleText = "Lorem ipsum"
                        messageText =
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod"
                        addPositiveButton("ok") {
                            Toast.makeText(
                                requireActivity(),
                                "positive button clicked",
                                    Toast.LENGTH_SHORT
                            ).show()
                        }
                        animResource = R.raw.info
                        loopAnimation = true
                    }.build().show()
                }
                11 -> {
                    SapDialog(requireActivity()).apply {
                        titleText = "Lorem ipsum"
                        messageText =
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod"
                        addPositiveButton("ok") {
                            Toast.makeText(
                                requireActivity(),
                                "positive button clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        addNegativeButton("cancel") {
                            Toast.makeText(
                                requireActivity(),
                                "negative button clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        addCancelEvent {
                            Toast.makeText(
                                requireActivity(),
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
                    // too lazy to change below numbers
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
                16->{
                    showInfoDialog(infoStr = "you are informed",isCancellable = true)
                }
                17 -> {
                    showLoadingDialog(isCancellable = true){
                        Toast.makeText(requireContext(),"dialog cancelled",Toast.LENGTH_SHORT).show()
                    }
                }
                18 -> {
                    showErrorDialog(errorRes = R.string.dangerous_error,
                        positiveButtonStr = "ok")
                }
                19->{
                    showWarningDialog(warningStr = "Seals are too alone",
                        positiveButtonStr = "Be sad",
                        positiveButtonClick = {
                            Toast.makeText(requireContext(),":(",Toast.LENGTH_SHORT).show()
                        }  ,
                        negativeButtonStr = "Cry",
                        negativeButtonClick = {
                            Toast.makeText(requireContext(),":_(",Toast.LENGTH_SHORT).show()
                        }
                        )
                }
                20->{
                    showInfoDialog (infoStr = "7 is a prime number",
                        positiveButtonStr = "Cool",
                        negativeButtonStr = "Prove it",
                        negativeButtonClick = {
                            for(i in 6 downTo 2){
                                Toast.makeText(requireContext(),"7/$i = "+(7.0/i).toString(),
                                    Toast.LENGTH_SHORT).show()
                            }
                            Toast.makeText(requireContext(),
                                "Told ya!",Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }

    companion object{
        fun getInstance() = DemoFragment()
    }
}