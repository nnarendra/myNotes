package com.test.mynotes.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.mynotes.R
import com.test.mynotes.databinding.ActivityHomeBinding
import com.test.mynotes.ui.fragments.HomeFragment


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
       this.supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, HomeFragment())
            .commitAllowingStateLoss()

    }
}