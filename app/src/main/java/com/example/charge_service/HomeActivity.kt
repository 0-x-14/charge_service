package com.example.charge_service

<<<<<<< HEAD
<<<<<<< HEAD
import android.content.Context
=======
import RentalFragment
import android.content.Intent
>>>>>>> d5c247d ([혜진] 부분 수정)
=======
import android.content.Intent
>>>>>>> f08d0de ([주영] 지도 및 대여, 로그아웃 수정)
import android.os.Bundle
<<<<<<< HEAD
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
=======
import android.util.Log
>>>>>>> 3ade8a7 ([주영] 반납 화면전환 보완)
import androidx.appcompat.app.AppCompatActivity
<<<<<<< HEAD
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.charge_service.databinding.ActivityMainBinding
<<<<<<< HEAD
=======
import androidx.fragment.app.Fragment
>>>>>>> 36e671c (QR 스캔 처리 갯수 업데이트 되는거까지 구현 완료)
import com.example.charge_service.databinding.HomeBinding
import com.example.charge_service.databinding.MainHeaderBinding
import com.example.charge_service.databinding.NavigationHeaderBinding
=======
>>>>>>> c32cf80 ([주영] 일부 파일 정리)
import com.google.android.material.bottomnavigation.BottomNavigationView
<<<<<<< HEAD
import com.google.android.material.navigation.NavigationView
=======
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
>>>>>>> 3ade8a7 ([주영] 반납 화면전환 보완)

class HomeActivity: AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
<<<<<<< HEAD
    private val HomeFragment by lazy { HomeFragment() }
<<<<<<< HEAD
    private val ReturnFragment by lazy {ReturnFragment()}
    private val PayFragment by lazy {PayFragment()}
    private val RentalConditionFragment by lazy {RentalConditionFragment()}
    //private val binding by lazy { HomeBinding.inflate(layoutInflater) }
    private lateinit var preferences: PreferenceUtil
    private lateinit var databaseReference: DatabaseReference // databaseReference 선언

<<<<<<< HEAD
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
=======
    private val HomeUsingFragment by lazy {HomeUsingFragment()}
    private val RentalCompFragment by lazy {RentalCompFragment()}
    private val ReturnCompFragment by lazy {ReturnCompFragment()}
=======
    private val ReturnFragment by lazy { ReturnFragment() }
    private val PayFragment by lazy { PayFragment() }
    private val RentalConditionFragment by lazy { RentalConditionFragment() }
<<<<<<< HEAD
=======
    private val binding by lazy { HomeBinding.inflate(layoutInflater) }
    private lateinit var preferences: PreferenceUtil
    private var rentalFragment: RentalFragment? = null
>>>>>>> d5c247d ([혜진] 부분 수정)
    //private vmeBinding.inflate(layoutInflater) }

    private val HomeUsingFragment by lazy { HomeUsingFragment() }
    private val RentalCompFragment by lazy { RentalCompFragment() }
    private val ReturnCompFragment by lazy { ReturnCompFragment() }
>>>>>>> 975da55 (HomeActivity 수정)
    // 각각 대여/반납 완료 화면이 정상적으로 구현되었는지 확인할 때 사용하는 용도
<<<<<<< HEAD
>>>>>>> 96ae0fd ([주영] 지도 API 재시도 -1)
=======
=======
    private val ReturnFragment by lazy { ReturnFragment() }
    private val PayFragment by lazy { PayFragment() }
    private val RentalConditionFragment by lazy { RentalConditionFragment() }
    private lateinit var preferences: PreferenceUtil

    private val HomeUsingFragment by lazy { HomeUsingFragment() }
>>>>>>> c32cf80 ([주영] 일부 파일 정리)

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    // HomeActivity onCreate 내부의 메뉴 아이템 찾기 예시


>>>>>>> d5c247d ([혜진] 부분 수정)
    override fun onCreate(savedInstanceState: Bundle?) {

        // navigationViewHeader 초기화
        preferences = PreferenceUtil(applicationContext)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        drawerLayout = binding.homeLayout
        navigationView = binding.homeNavigation
        super.onCreate(savedInstanceState)

<<<<<<< HEAD
        if (savedInstanceState == null) {
=======
        // databaseReference 초기화
        databaseReference = FirebaseDatabase.getInstance().getReference("rental_time")

        if(savedInstanceState == null) {
>>>>>>> 3ade8a7 ([주영] 반납 화면전환 보완)
            setUpBottomNavigationBar()
        }
<<<<<<< HEAD

        //로그인 후 id값을 받아와 사용자명 변경
        val id = preferences.getString("id", "") ?: ""
        val userName = findViewById<TextView>(R.id.user)
        if (userName != null) {
            userName.setText("${id}님 안녕하세요")
        }

        val open = findViewById<ImageView>(R.id.menu_btn)

<<<<<<< HEAD
        val alarm = findViewById<ImageView>(R.id.alarm_btn)

        alarm.setOnClickListener{
            val intent = Intent(this, AlarmActivity::class.java)
            startActivity(intent)
        }
=======
>>>>>>> f08d0de ([주영] 지도 및 대여, 로그아웃 수정)
        open.setOnClickListener {
            val drawer = findViewById<DrawerLayout>(R.id.homeLayout)
            if (!drawer.isDrawerOpen(GravityCompat.END)) {
                drawer.openDrawer(GravityCompat.END)
            }
        }
<<<<<<< HEAD

<<<<<<< HEAD
=======
>>>>>>> 0fd952f ([주영] rental 화면전환 업데이트)
=======
=======
>>>>>>> f08d0de ([주영] 지도 및 대여, 로그아웃 수정)
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
<<<<<<< HEAD
>>>>>>> d5c247d ([혜진] 부분 수정)
=======
>>>>>>> f08d0de ([주영] 지도 및 대여, 로그아웃 수정)
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
<<<<<<< HEAD
                    { changeFragment(HomeFragment)
<<<<<<< HEAD
=======
                    {
                        changeFragment(HomeUsingFragment)
                        // changeFragment(HomeFragment)
<<<<<<< HEAD
//                        val userName = findViewById<TextView>(R.id.textView)
//                        val id = preferences.getString("id", "")
//                        userName.text = "${id} 님 안녕하세요"
>>>>>>> 96ae0fd ([주영] 지도 API 재시도 -1)
=======
>>>>>>> 0fd952f ([주영] rental 화면전환 업데이트)
                    true
=======
                        true
>>>>>>> 975da55 (HomeActivity 수정)
                    }
                    R.id.navi_rental -> {
                        changeFragment(RentalConditionFragment)
                        // checkAndChangeFragment(RentalConditionFragment)
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

<<<<<<< HEAD
        fun changeFragment(fragment: Fragment) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.navi_fragment_container, fragment)
                .commit()
        }
=======

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.navi_fragment_container, fragment)
            .commit()
    }

//    private fun checkAndChangeFragment(fragment: Fragment) {
//        // 로그 찍어서 확인해보니 checkAndChangeFragment가 아예 실행이 안되고 있는 듯
//        Log.d("jupy", "확인 지점 -0")
//        // databaseReference.addListener ~~ 부분이 아예 실행 안되고 로그인 화면으로 튕김
//        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
//            // ChildEventListener
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                Log.d("jupy", "확인 지점 -5")
//                if (dataSnapshot.exists()) {
//                    Log.d("jupy", "확인 지점 -6")
//                    // Firebase에 정보가 저장되어 있다면 대여 중인 상태로 판단
//                    when (fragment) {
//                        is RentalConditionFragment -> {
//                            changeFragment(RentalCompFragment())
//                            Log.d("jupy", "확인 지점 -7")
//                        }
//                        else -> {
//                            changeFragment(fragment)
//                            Log.d("jupy", "확인 지점 -8")
//                        }
//                    }
//                } else {
//                    // Firebase에 정보가 없다면 대여 중인 상태가 아님
//                    Log.d("jupy", "확인 지점 -9")
//                    changeFragment(fragment)
//                    Log.d("jupy", "확인 지점 -10")
//                }
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // 에러 처리
//            }
//        })
//    }



    fun switchToRentalCompFragment() {
        changeFragment(RentalCompFragment())
    }

    fun switchToReturnCompFragment() {
        changeFragment(ReturnCompFragment())
    }

>>>>>>> 0fd952f ([주영] rental 화면전환 업데이트)
}