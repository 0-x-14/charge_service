package com.example.charge_service

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.charge_service.databinding.HomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeActivity: AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private val HomeFragment by lazy { HomeFragment() }
    private val ReturnFragment by lazy {ReturnFragment()}
    private val PayFragment by lazy {PayFragment()}
    private val RentalConditionFragment by lazy {RentalConditionFragment()}
    private val binding by lazy { HomeBinding.inflate(layoutInflater) }
    private lateinit var preferences: PreferenceUtil
    private lateinit var databaseReference: DatabaseReference // databaseReference 선언

    private val HomeUsingFragment by lazy {HomeUsingFragment()}
    private val RentalCompFragment by lazy {RentalCompFragment()}
    private val ReturnCompFragment by lazy {ReturnCompFragment()}
    // 각각 대여/반납 완료 화면이 정상적으로 구현되었는지 확인할 때 사용하는 용도
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferences = PreferenceUtil(applicationContext)

        // databaseReference 초기화
        databaseReference = FirebaseDatabase.getInstance().getReference("rental_time")

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
                    {
                        changeFragment(HomeUsingFragment)
                        // changeFragment(HomeFragment)
                    true
                    }
                    R.id.navi_rental -> {
                        // changeFragment(RentalCompFragment)
                        changeFragment(RentalConditionFragment)
                        // checkAndChangeFragment(RentalConditionFragment)
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

}