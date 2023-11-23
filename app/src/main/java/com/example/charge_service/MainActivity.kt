package com.example.charge_service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE = 1000 // 권한 요청 코드
    private var isPermissionsRequested = false // 권한 요청 여부를 나타내는 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rental)

            val btnScan: Button = findViewById(R.id.rental_button)
            btnScan.setOnClickListener {
                // MainActivity에서 ScanQRActivity로 이동하는 Intent 생성
                val intent = Intent(this@MainActivity,ScanQRActivity::class.java)
                startActivity(intent) // ScanQRActivity 시작
            }

        // 위치 권한 확인
        val locationPermission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        // 카메라 권한 확인
        val cameraPermission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA
        )
        // 알람 권한 확인
        val alarmPermission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.SET_ALARM
        )

        // 권한이 열려있는지 확인
        if (locationPermission == PackageManager.PERMISSION_DENIED ||
            cameraPermission == PackageManager.PERMISSION_DENIED ||
            alarmPermission == PackageManager.PERMISSION_DENIED
        ) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val permissionsToRequest = mutableListOf<String>()

                // 위치 권한이 없는 경우 권한 요청 리스트에 추가
                if (locationPermission == PackageManager.PERMISSION_DENIED) {
                    permissionsToRequest.add(android.Manifest.permission.ACCESS_FINE_LOCATION)
                }
                // 카메라 권한이 없는 경우 권한 요청 리스트에 추가
                if (cameraPermission == PackageManager.PERMISSION_DENIED) {
                    permissionsToRequest.add(android.Manifest.permission.CAMERA)
                }
                // 알람 권한이 없는 경우 권한 요청 리스트에 추가
                if (alarmPermission == PackageManager.PERMISSION_DENIED) {
                    permissionsToRequest.add(android.Manifest.permission.SET_ALARM)
                }

                // 권한 요청
                requestPermissions(
                    permissionsToRequest.toTypedArray(),
                    PERMISSION_REQUEST_CODE
                )
                isPermissionsRequested = true // 권한 요청 상태를 나타내는 플래그 업데이트
            }

        } else {

            //권한 이미 부여된 경우 실행할 작업(허용된 후에)
        }
    }

    // 권한 요청 결과 처리
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            var allPermissionsGranted = true

            // 권한 요청 결과 확인
            for (result in grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false
                    break
                }
            }

            // 모든 권한이 허용되었는지 확인하고 작업 수행
            if (allPermissionsGranted) {
                // 권한 허용됨 -다음 원하는 작업 수행
            } else {
                // 권한 거부됨 - 필요한 처리 수행 사용자에게 권한이 필요하다는 메시지 표시
            }
            isPermissionsRequested = false // 권한 요청 결과를 처리했으므로 플래그 업데이트
        }
    }

    override fun onResume() {
        super.onResume()
        if (isPermissionsRequested) {
            // 권한 요청 다이얼로그가 떠있는 동안에도 이 화면을 유지하기 위한 처리
        }
    }
}
