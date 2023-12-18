package com.example.charge_service
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import android.widget.Button
=======
>>>>>>> df5ce6c (로그인, main화면 수정,이용약관 스크롤 수정)
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import com.example.charge_service.MainActivity.Companion.preferences
<<<<<<< HEAD
import com.example.charge_service.databinding.RentalBinding
import com.example.charge_service.databinding.ReturnPageBinding
=======
//import com.example.charge_service.databinding.ReturnComp4Binding
import com.example.charge_service.databinding.ReturnPageBinding
import android.widget.Button
//>>>>>>> bd686f0bd44fcc32dfb786796d152e4888390a3b
>>>>>>> df5ce6c (로그인, main화면 수정,이용약관 스크롤 수정)


class ReturnFragment : Fragment() {
    // TODO: Rename and change types of parameters
<<<<<<< HEAD
=======

>>>>>>> df5ce6c (로그인, main화면 수정,이용약관 스크롤 수정)
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
<<<<<<< HEAD
            //param1 = it.getString(ARG_PARAM1)
            //param2 = it.getString(ARG_PARAM2)
=======
            param1 = it.getString(param1)
            param2 = it.getString(param2)
>>>>>>> df5ce6c (로그인, main화면 수정,이용약관 스크롤 수정)
        }
=======
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import com.example.charge_service.MainActivity.Companion.preferences
import com.example.charge_service.databinding.ReturnCompBinding
import com.example.charge_service.databinding.ReturnPageBinding
import android.widget.Button


class ReturnFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
>>>>>>> f59e417 (주영 타이머 구현 -1 (미완성))
=======
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import com.example.charge_service.MainActivity.Companion.preferences
import com.example.charge_service.databinding.ReturnCompBinding
import com.example.charge_service.databinding.ReturnPageBinding
import android.widget.Button


class ReturnFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
>>>>>>> ed8d295 ([주영] 타이머 구현 -3(재))
=======
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import com.example.charge_service.MainActivity.Companion.preferences
import com.example.charge_service.databinding.ReturnCompBinding
import com.example.charge_service.databinding.ReturnPageBinding
import android.widget.Button


class ReturnFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
>>>>>>> 2651ef1 ([주영] 타이머 구현 -3)
        // return page에 대한 xml
        val binding = ReturnPageBinding.inflate(layoutInflater)

//        val userName = findViewById<TextView>(R.id.textView)
//        val id = preferences.getString("id", "")
//        userName.text = "${id} 님 안녕하세요"
        // 눈송이를 id값으로 변경
        // 메뉴와 알람 누르면 이벤트 처리
<<<<<<< HEAD
//        binding.menuBtn.setOnClickListener{
//            binding.homeLayout.openDrawer(GravityCompat.END)
//        }
//
//        binding.alarmBtn.setOnClickListener{
//
//        }

    }
<<<<<<< HEAD
=======
//=======
//>>>>>>> bd686f0bd44fcc32dfb786796d152e4888390a3b
>>>>>>> df5ce6c (로그인, main화면 수정,이용약관 스크롤 수정)
=======
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
>>>>>>> 36e671c (QR 스캔 처리 갯수 업데이트 되는거까지 구현 완료)
=======
        binding.menuBtn.setOnClickListener{
            binding.homeLayout.openDrawer(GravityCompat.END)
        }
        binding.alarmBtn.setOnClickListener{
        }

    }
<<<<<<< HEAD
>>>>>>> ed8d295 ([주영] 타이머 구현 -3(재))
=======
>>>>>>> 2651ef1 ([주영] 타이머 구현 -3)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
<<<<<<< HEAD
        binding = ReturnPageBinding.inflate(inflater, container, false)
        return binding.root
    }

<<<<<<< HEAD
}

=======
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
>>>>>>> 36e671c (QR 스캔 처리 갯수 업데이트 되는거까지 구현 완료)
=======
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
<<<<<<< HEAD
}
>>>>>>> ed8d295 ([주영] 타이머 구현 -3(재))
=======
}
>>>>>>> 2651ef1 ([주영] 타이머 구현 -3)
