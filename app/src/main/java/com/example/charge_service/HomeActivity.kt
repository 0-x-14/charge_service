package com.example.charge_service

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.charge_service.databinding.HomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity: AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private val HomeFragment by lazy { HomeFragment() }
    private val ReturnFragment by lazy {ReturnFragment()}
    private val PayFragment by lazy {PayFragment()}
    private val RentalConditionFragment by lazy {RentalConditionFragment()}
    private val binding by lazy { HomeBinding.inflate(layoutInflater) }
    private lateinit var preferences: PreferenceUtil

    private val RentalCompFragment by lazy {RentalCompFragment()}
    private val ReturnCompFragment by lazy {ReturnCompFragment()}
    // 각각 대여/반납 완료 화면이 정상적으로 구현되었는지 확인할 때 사용하는 용도
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferences = PreferenceUtil(applicationContext)

        if(savedInstanceState == null) {
            setUpBottomNavigationBar()
        }
        //val userName = binding.textView
//        val userName = findViewById<TextView>(R.id.user)
//        val id = preferences.getString("id", "")
//        userName.text = "${id} 님 안녕하세요"
    }


    private fun setUpBottomNavigationBar() {
        bottomNavigationView = findViewById(R.id.Smenu)
        bottomNavigationView.run {
            setOnNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.navi_home ->
                    { changeFragment(HomeFragment)
//                        val userName = findViewById<TextView>(R.id.textView)
//                        val id = preferences.getString("id", "")
//                        userName.text = "${id} 님 안녕하세요"
                    true
                    }
                    R.id.navi_rental -> {
                        changeFragment(RentalCompFragment)
                        // changeFragment(RentalConditionFragment)
                        true
                    }
                    R.id.navi_return -> {
                        // changeFragment(ReturnCompFragment)
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