package com.example.charge_service


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.zxing.integration.android.IntentIntegrator
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class QRScanActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var urls: Array<String> // URL을 담을 배열

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qr)

        // Firebase Database 인스턴스 초기화
        database = FirebaseDatabase.getInstance()

        // 대여할 URL 배열 초기화
        urls = arrayOf(
            "https://m.site.naver.com/1geRx",
            "https://m.site.naver.com/1geRp",
            "https://m.site.naver.com/1geRj",
            "https://m.site.naver.com/1geRa",
            "https://m.site.naver.com/1geR5",
            "https://m.site.naver.com/1geQZ",
            "https://m.site.naver.com/1geQV",
            "https://m.site.naver.com/1geQL",
            "https://m.site.naver.com/1geQD"
        )

        startQRScanner()
        saveCurrentTimeToFirebase() // Firebase에 현재 시간 저장
    }

    private fun startQRScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setOrientationLocked(false)
        integrator.initiateScan()
    }

    // onActivityResult를 오버라이드하여 스캔 결과를 처리
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                // 취소됨
                Toast.makeText(this, "취소됨", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                // 스캔 결과에서 URL 가져오기
                val scannedUrl = result.contents
                Toast.makeText(this, "스캔 성공: $scannedUrl", Toast.LENGTH_SHORT).show()

                // URL이 배열에 있는지 확인
                if (urls.contains(scannedUrl)) {
                    // 해당 URL이 배열에 있다면 Firebase에서 갯수를 줄임
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
        val timeRef = database.getReference("urls/$url/count") // 해당 URL의 count 필드에 접근

        // 현재 갯수 가져오기
        timeRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val currentCount = dataSnapshot.getValue(Int::class.java) ?: 0

                // 갯수가 0보다 클 경우에만 감소시킴
                if (currentCount > 0) {
                    val updatedCount = currentCount - 1
                    timeRef.setValue(updatedCount) // Firebase에 감소된 갯수 업데이트
                    Toast.makeText(this@QRScanActivity, "해당 URL의 대여 갯수가 줄었습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@QRScanActivity, "더 이상 대여 가능한 갯수가 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@QRScanActivity, "데이터베이스 에러: ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // Firebase에 현재 시간을 저장하는 함수
    private fun saveCurrentTimeToFirebase() {
        val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val timeRef = database.getReference("current_time")
        timeRef.setValue(currentTime)
            .addOnSuccessListener {
                Toast.makeText(this@QRScanActivity, "현재 시간이 성공적으로 저장되었습니다.", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this@QRScanActivity, "시간을 저장하는 데 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
    }
}
