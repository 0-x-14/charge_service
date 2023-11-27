package com.example.charge_service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.charge_service.databinding.ActivityMainBinding
import com.example.charge_service.databinding.RentalBinding
import com.google.firebase.database.*
import com.google.zxing.integration.android.IntentIntegrator
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class QRScanActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var urls: Array<String>
    private lateinit var binding: RentalBinding // 변경된 부분

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RentalBinding.inflate(layoutInflater) // 변경된 부분
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()

        urls = arrayOf(
            "https://m.site.naver.com/1geRx",
            // Add other URLs as needed
        )

        binding.rentalButton.setOnClickListener {
            val currentCount = binding.numOfEightPin.text.toString().toIntOrNull() ?: 0
            if (currentCount > 0) {
                val updatedCount = currentCount - 1
                binding.numOfEightPin.text = updatedCount.toString()
                startQRScanner()
            } else {
                Toast.makeText(
                    this@QRScanActivity,
                    "더 이상 대여 가능한 갯수가 없습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        startQRScanner()
        saveCurrentTimeToFirebase()
    }

    private fun startQRScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setOrientationLocked(false)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "취소됨", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                val scannedUrl = result.contents
                Toast.makeText(this, "대여 완료: $scannedUrl", Toast.LENGTH_SHORT).show()

                if (urls.contains(scannedUrl)) {
                    decreaseRentCount(scannedUrl)
                } else {
                    Toast.makeText(this, "해당 URL은 대여할 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun decreaseRentCount(url: String) {
        val timeRef = database.getReference("urls").child(url).child("count")

        timeRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val currentCount = dataSnapshot.getValue(Int::class.java) ?: 0

                if (currentCount > 0) {
                    val updatedCount = currentCount - 1
                    timeRef.setValue(updatedCount)
                    Toast.makeText(
                        this@QRScanActivity,
                        "해당 URL의 대여 갯수가 줄었습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.numOfEightPin.text = updatedCount.toString()
                } else {
                    Toast.makeText(
                        this@QRScanActivity,
                        "더 이상 대여 가능한 갯수가 없습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    this@QRScanActivity,
                    "데이터베이스 에러: ${databaseError.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun saveCurrentTimeToFirebase() {
        val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val timeRef = database.getReference("current_time")
        timeRef.setValue(currentTime)
            .addOnSuccessListener {
                Toast.makeText(
                    this@QRScanActivity,
                    "현재 시간이 성공적으로 저장되었습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener {
                Toast.makeText(
                    this@QRScanActivity,
                    "시간을 저장하는 데 실패했습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}
