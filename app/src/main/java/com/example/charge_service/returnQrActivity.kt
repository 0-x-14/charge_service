package com.example.charge_service

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator

class returnQrActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.return_chargimg)

        val rentalButton1 = findViewById<Button>(R.id.returnButton1)
        val rentalButton2 = findViewById<Button>(R.id.returnButton2)
        val rentalButton3 = findViewById<Button>(R.id.returnButton3)

        rentalButton1.setOnClickListener {
            startQRScanner()
        }

        rentalButton2.setOnClickListener {
            startQRScanner()
        }

        rentalButton3.setOnClickListener {
            startQRScanner()
        }
    }

    private fun startQRScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setOrientationLocked(false)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "취소됨", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    // 처리가 성공했다는 토스트 메시지를 띄웁니다.
                    Toast.makeText(this, "반납되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
