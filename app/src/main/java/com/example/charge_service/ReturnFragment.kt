package com.example.charge_service

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class ReturnFragment : Fragment() {
    // TODO: Rename and change types of parameters
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
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