package com.example.charge_service

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import com.example.charge_service.MainActivity.Companion.preferences
import com.example.charge_service.databinding.ReturnComp4Binding
import com.example.charge_service.databinding.ReturnPageBinding
=======
import android.widget.Button
>>>>>>> bd686f0bd44fcc32dfb786796d152e4888390a3b


class ReturnFragment : Fragment() {
    // TODO: Rename and change types of parameters
<<<<<<< HEAD
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        // return page에 대한 xml
        val binding = ReturnPageBinding.inflate(layoutInflater)

//        val userName = findViewById<TextView>(R.id.textView)
//        val id = preferences.getString("id", "")
//        userName.text = "${id} 님 안녕하세요"
        // 눈송이를 id값으로 변경

        // 메뉴와 알람 누르면 이벤트 처리
        binding.menuBtn.setOnClickListener{
            binding.homeLayout.openDrawer(GravityCompat.END)
        }

        binding.alarmBtn.setOnClickListener{

        }

    }
=======
>>>>>>> bd686f0bd44fcc32dfb786796d152e4888390a3b

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 기본 화면 세팅
        val view = inflater.inflate(R.layout.return_page, container, false)

        // 각 버튼 클릭시 QR코드 스캔으로 넘어감
        val btnScan1: Button = view.findViewById(R.id.returnButton1)
        btnScan1.setOnClickListener {
            // MainActivity에서 ScanQRActivity로 이동하는 Intent 생성
            val intent = Intent(getActivity(), QRReturnActivity::class.java)
            startActivity(intent) // ScanQRActivity 시작
        }

        val btnScan2: Button = view.findViewById(R.id.returnButton2)
        btnScan2.setOnClickListener {
            // MainActivity에서 ScanQRActivity로 이동하는 Intent 생성
            val intent = Intent(getActivity(), QRReturnActivity::class.java)
            startActivity(intent) // ScanQRActivity 시작
        }

        val btnScan3: Button = view.findViewById(R.id.returnButton3)
        btnScan3.setOnClickListener {
            // MainActivity에서 ScanQRActivity로 이동하는 Intent 생성
            val intent = Intent(getActivity(), QRReturnActivity::class.java)
            startActivity(intent) // ScanQRActivity 시작
        }

        return view
    }

}