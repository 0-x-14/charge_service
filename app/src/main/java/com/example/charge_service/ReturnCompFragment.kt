package com.example.charge_service

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class ReturnCompFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 기본 화면 세팅
        val view = inflater.inflate(R.layout.return_comp, container, false)

        // submission 버튼을 누르면 파이어베이스에 저장 후 홈화면으로 이동함
        // 파이어베이스 부분 구현 X
        val submission: Button = view.findViewById(R.id.submission)

        submission.setOnClickListener {
            val homeActivity = requireActivity() as HomeActivity
            homeActivity.changeFragment(HomeUsingFragment())
        }

        val name = view.findViewById<TextView>(R.id.textView16)
        val id = MainActivity.preferences.getString("id", "") ?: ""
        if (name != null) {
            name.setText("${id}님")
        }

        return view
    }
}