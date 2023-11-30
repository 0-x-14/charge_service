package com.example.charge_service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.charge_service.databinding.RentalBinding
import com.google.firebase.database.*
import com.google.zxing.integration.android.IntentIntegrator
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class QRScanActivity : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    private lateinit var urls1: Array<String>
    private lateinit var urls2: Array<String>
    private lateinit var urls3: Array<String>
    private lateinit var binding: RentalBinding // 변경된 부분
    private val RentalCompFragment by lazy { RentalCompFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RentalBinding.inflate(layoutInflater) // 변경된 부분
        setContentView(binding.root)


        val output_text1 = findViewById<TextView>(R.id.numOfEightPin)
        val output_text2 = findViewById<TextView>(R.id.numOfCtype)
        val output_text3 = findViewById<TextView>(R.id.numOfnote)
        var num1 = 3
        var num2 = 3
        var num3 = 3

        urls1 = arrayOf(
            "https://m.site.naver.com/1geRx",
            "https://m.site.naver.com/1geRp",
            "https://m.site.naver.com/1geRj",
        )
        urls2 = arrayOf(
            "https://m.site.naver.com/1geRa",
            "https://m.site.naver.com/1geR5",
            "https://m.site.naver.com/1geQZ",
        )
        urls3 = arrayOf(
            "https://m.site.naver.com/1geQV",
            "https://m.site.naver.com/1geQL",
            "https://m.site.naver.com/1geQD"
        )

        binding.rentalButton1.setOnClickListener {
            if (num1 > 0) {
                num1 -= 1
                binding.numOfEightPin.text = num1.toString()
                startQRScanner()
            } else {
                Toast.makeText(
                    this@QRScanActivity,
                    "더 이상 대여 가능한 개수가 없습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.rentalButton2.setOnClickListener {
            if (num2 > 0) {
                num2 -= 1
                binding.numOfCtype.text = num2.toString()
                startQRScanner()
            } else {
                Toast.makeText(
                    this@QRScanActivity,
                    "더 이상 대여 가능한 개수가 없습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.rentalButton3.setOnClickListener {
            if (num3 > 0) {
                num3 -= 1
                binding.numOfnote.text = num3.toString()
                startQRScanner()
            } else {
                Toast.makeText(
                    this@QRScanActivity,
                    "더 이상 대여 가능한 개수가 없습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        startQRScanner()
        //saveCurrentTimeToFirebase()
    }

    private fun startQRScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setOrientationLocked(false)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result1 = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result1 != null) {
            if (result1.contents == null) {
                Toast.makeText(this, "취소됨", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                val scannedUrl = result1.contents
                Toast.makeText(this, "8핀 충전기 대여 완료: $scannedUrl", Toast.LENGTH_SHORT).show()

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

        val result2 = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result2 != null) {
            if (result2.contents == null) {
                Toast.makeText(this, "취소됨", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                val scannedUrl = result2.contents
                Toast.makeText(this, "C타입 충전기 대여 완료: $scannedUrl", Toast.LENGTH_SHORT).show()

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
        val result3 = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result3 != null) {
            if (result3.contents == null) {
                Toast.makeText(this, "취소됨", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                val scannedUrl = result3.contents
                Toast.makeText(this, "노트북 충전기 대여 완료: $scannedUrl", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}