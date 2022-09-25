package com.example.common_base

import android.os.Bundle
import com.example.common_base.base.BaseAppCompatActivity
import com.example.common_base.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : BaseAppCompatActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}