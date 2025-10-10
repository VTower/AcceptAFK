package com.example.acceptafk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.acceptafk.ui.main.ActivitAcceptMatchFragment

class ActivitAcceptMatch : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activit_accept_match)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ActivitAcceptMatchFragment.newInstance())
                .commitNow()
        }
    }
}