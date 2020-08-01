package com.alperenbabagil.simpleanimationpopup

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class FragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_activity)
        supportFragmentManager.beginTransaction().
            replace(R.id.fragmentContainer,DemoFragment.getInstance()).commit()
    }
}