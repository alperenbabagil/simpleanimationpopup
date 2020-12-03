package com.alperenbabagil.simpleanimationpopuplibrary

import android.view.View
import androidx.activity.OnBackPressedCallback

interface SapFragment {
    var currentDialogView: View?

    var onBackPressedCallback: OnBackPressedCallback?
}