package com.example.charge_service

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.charge_service.databinding.ReturnPageBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.zxing.integration.android.IntentIntegrator
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ReturnFragment : Fragment() {
    private lateinit var binding: ReturnPageBinding
    private lateinit var database: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ReturnPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = FirebaseDatabase.getInstance()

        binding.returnButton1.setOnClickListener {
            startQRScanner(1)
            saveCurrentTimeToFirebase()
        }

        binding.returnButton2.setOnClickListener {
            startQRScanner(2)
            saveCurrentTimeToFirebase()
        }

        binding.returnButton3.setOnClickListener {
            startQRScanner(3)
            saveCurrentTimeToFirebase()
        }
    }

    private fun startQRScanner(buttonId: Int) {
        val integrator = IntentIntegrator.forSupportFragment(this)
        integrator.setOrientationLocked(false)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == AppCompatActivity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    // 각 버튼에 따른 취소 처리
                    when (requestCode) {
                        1 -> { /* 버튼 1에 대한 취소 처리 */ }
                        2 -> { /* 버튼 2에 대한 취소 처리 */ }
                        3 -> { /* 버튼 3에 대한 취소 처리 */ }
                    }
                    Toast.makeText(requireContext(), "취소됨", Toast.LENGTH_SHORT).show()
                    requireActivity().finish()
                } else {
                    // 각 버튼에 따른 성공 처리
                    when (requestCode) {

                        1 -> {  Toast.makeText(requireContext(), "8핀 충전기 반납이 완료되었습니다.", Toast.LENGTH_LONG).show() }
                        2 -> { Toast.makeText(requireContext(), "C타입 충전기 반납이 완료되었습니다.", Toast.LENGTH_LONG).show() }
                        3 -> { Toast.makeText(requireContext(), "노트북 충전기 반납이 완료되었습니다.", Toast.LENGTH_LONG).show() }
                    }
                    saveCurrentTimeToFirebase()
                    // 처리가 성공했다는 토스트 메시지를 띄웁니다.
                    Toast.makeText(requireContext(), "충전기 반납이 완료되었습니다.", Toast.LENGTH_SHORT).show()

//                    // Fragment를 교체하는 코드
//                    val returnCompFragment = ReturnCompFragment()
//                    val transaction = requireFragmentManager().beginTransaction()
//                    transaction.replace(R.id.navi_fragment_container, returnCompFragment)
//                    transaction.commit()
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    private fun saveCurrentTimeToFirebase() {
        val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val timeRef = database.getReference("return_time").push()
        timeRef.setValue(currentTime)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "현재 시간이 성공적으로 저장되었습니다.", Toast.LENGTH_SHORT).show()
                (activity as? HomeActivity)?.switchToReturnCompFragment()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "시간을 저장하는 데 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
    }
}