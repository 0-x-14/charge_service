package com.example.charge_service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.core.view.GravityCompat
import com.example.charge_service.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view1)

    }
}
//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.menuBtn.setOnClickListener {
//            binding.homeLayout.openDrawer(GravityCompat.END)
//        }
//    }
//}

