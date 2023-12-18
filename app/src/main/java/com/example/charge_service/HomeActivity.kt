package com.example.charge_service

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.charge_service.MainActivity.Companion.preferences
import com.example.charge_service.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class HomeActivity: AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private val HomeFragment by lazy { HomeFragment() }
    private val ReturnFragment by lazy { ReturnFragment() }
    private val PayFragment by lazy { PayFragment() }
    private val RentalConditionFragment by lazy { RentalConditionFragment() }
    //private vmeBinding.inflate(layoutInflater) }

    private val HomeUsingFragment by lazy { HomeUsingFragment() }
    private val RentalCompFragment by lazy { RentalCompFragment() }
    private val ReturnCompFragment by lazy { ReturnCompFragment() }
    // 각각 대여/반납 완료 화면이 정상적으로 구현되었는지 확인할 때 사용하는 용도

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {

        // navigationViewHeader 초기화
        preferences = PreferenceUtil(applicationContext)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        drawerLayout = binding.homeLayout
        navigationView = binding.homeNavigation
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            setUpBottomNavigationBar()
        }

        //로그인 후 id값을 받아와 사용자명 변경
        val id = preferences.getString("id", "") ?: ""
        val userName = findViewById<TextView>(R.id.user)
        if (userName != null) {
            userName.setText("${id}님 안녕하세요")
        }

        val open = findViewById<ImageView>(R.id.menu_btn)
        open.setOnClickListener {
            val drawer = findViewById<DrawerLayout>(R.id.homeLayout)
            if (!drawer.isDrawerOpen(GravityCompat.END)) {
                drawer.openDrawer(GravityCompat.END)
            }
        }

    }
    private fun setUpBottomNavigationBar() {
        bottomNavigationView = findViewById(R.id.Smenu)
        bottomNavigationView.run {
            setOnNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.navi_home ->
                    { changeFragment(HomeFragment)
                        true
                    }
                    R.id.navi_rental -> {
                        // changeFragment(RentalCompFragment)
                        changeFragment(RentalConditionFragment)
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
    fun switchToRentalCompFragment() {
        changeFragment(RentalCompFragment())
    }

    fun switchToReturnCompFragment() {
        changeFragment(ReturnCompFragment())
    }
    fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.navi_fragment_container, fragment)
            .commit()
    }
}