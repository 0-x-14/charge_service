package com.example.charge_service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.charge_service.databinding.ReturnPageBinding
import com.google.zxing.integration.android.IntentIntegrator

class QRReturnActivity : AppCompatActivity() {
    private lateinit var binding: ReturnPageBinding

    private val ReturnCompFragment by lazy {ReturnCompFragment()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ReturnPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.returnButton1.setOnClickListener {
            startQRScanner(1)
        }

        binding.returnButton2.setOnClickListener {
            startQRScanner(2)
        }

        binding.returnButton3.setOnClickListener {
            startQRScanner(3)
        }
    }

    private fun startQRScanner(buttonId: Int) {
        val integrator = IntentIntegrator(this)
        integrator.setOrientationLocked(false)
        integrator.initiateScan()
        // buttonId에 따른 추가적인 처리를 할 수 있도록 구현
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    // 각 버튼에 따른 취소 처리
                    when (requestCode) {
                        1 -> { /* 버튼 1에 대한 취소 처리 */ }
                        2 -> { /* 버튼 2에 대한 취소 처리 */ }
                        3 -> { /* 버튼 3에 대한 취소 처리 */ }
                    }
                    Toast.makeText(this, "취소됨", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    // 각 버튼에 따른 성공 처리
                    when (requestCode) {
                        1 -> { /* 버튼 1에 대한 성공 처리 */ }
                        2 -> { /* 버튼 2에 대한 성공 처리 */ }
                        3 -> { /* 버튼 3에 대한 성공 처리 */ }
                    }
                    // 처리가 성공했다는 토스트 메시지를 띄웁니다.
                    Toast.makeText(this, "충전기가 반납되었습니다.", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(R.id.navi_fragment_container, ReturnCompFragment)
                    // 반납이 성공할 경우 반납 완료 화면으로 이동함
                }
            }
        }
    }
}