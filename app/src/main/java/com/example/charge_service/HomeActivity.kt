package com.example.charge_service

import android.app.Fragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity: AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private val HomeFragment by lazy { HomeFragment() }
    private val ReturnFragment by lazy {ReturnFragment()}
    private val PayFragment by lazy {PayFragment()}
    private val RentalConditionFragment by lazy {RentalConditionFragment()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null) {
            setUpBottomNavigationBar()
        }
    }

    private fun setUpBottomNavigationBar() {
        bottomNavigationView = findViewById(R.id.Smenu)
        bottomNavigationView.run {
            setOnNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.navi_home ->
                    { changeFragment(HomeFragment)
                    true}
                    R.id.navi_rental -> {
                        changeFragment(RentalConditionFragment)
                        true
                    }
                    R.id.navi_return -> {
                        changeFragment(ReturnFragment)
                        true
                    }
                    R.id.navi_pay -> {
                        changeFragment(PayFragment)
                        true
                    }
                    else -> {
                        false
                    }
                } // 하단 네비게이션바를 클릭하면 각 프레그먼트가 화면에 보여짐
            }
            selectedItemId= R.id.navi_home
        }
    }

    internal fun changeFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.navi_fragment_container, fragment).commit()
    }
}