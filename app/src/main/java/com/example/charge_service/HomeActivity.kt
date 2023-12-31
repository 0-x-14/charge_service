package com.example.charge_service

import RentalFragment
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.charge_service.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class HomeActivity: AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private val ReturnFragment by lazy { ReturnFragment() }
    private val PayFragment by lazy { PayFragment() }
    private val RentalConditionFragment by lazy { RentalConditionFragment() }
    private lateinit var preferences: PreferenceUtil

    private val HomeUsingFragment by lazy { HomeUsingFragment() }

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    // HomeActivity onCreate 내부의 메뉴 아이템 찾기 예시


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

        val alarm = findViewById<ImageView>(R.id.alarm_btn)

        alarm.setOnClickListener{
            val intent = Intent(this, AlarmActivity::class.java)
            startActivity(intent)
        }
        open.setOnClickListener {
            val drawer = findViewById<DrawerLayout>(R.id.homeLayout)
            if (!drawer.isDrawerOpen(GravityCompat.END)) {
                drawer.openDrawer(GravityCompat.END)
            }
        }
        val navigationView: NavigationView = findViewById(R.id.home_navigation)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.logout -> {
                    // 로그아웃 버튼을 클릭했을 때의 동작
                    resetSharedPreferences()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
    fun resetSharedPreferences() {
        val sharedPreferences = getSharedPreferences("MyPrefs", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    private fun setUpBottomNavigationBar() {
        bottomNavigationView = findViewById(R.id.Smenu)
        bottomNavigationView.run {
            setOnNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.navi_home ->
                    { changeFragment(HomeUsingFragment)
                        true
                    }
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