package com.example.charge_service

import RentalFragment
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class RentalConditionFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 기본 화면 세팅
        val view = inflater.inflate(R.layout.rental_condition, container, false)

        // 이용약관 동의 버튼을 누르면 대여 화면으로 이동함
        val agree: Button = view.findViewById(R.id.rental_agree)
        agree.setOnClickListener {
            val homeActivity = requireActivity() as HomeActivity
            homeActivity.changeFragment(RentalFragment())
        }
        return view
    }
}