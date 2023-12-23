package com.example.charge_service

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.charge_service.databinding.AlarmRecordBinding

class AlarmActivity : AppCompatActivity() {

    private lateinit var binding: AlarmRecordBinding
    private lateinit var adapter: AlarmAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // xml과 연결
        binding = AlarmRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 레이아웃매니저 적용 - 오래된게 아래에 가게 reverse로 설정
        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        binding.recview.layoutManager = layoutManager

        val data = arrayListOf<String>("2023.12.01에 13:20 ~ 15:00까지 사용",
            "2023.12.01에\n15:10 ~ 16:55까지 사용", "C타입 충전기 1개가\n새로 들어왔습니다.", "2023.12.04에\n18:20 ~ 20:00까지 사용"
            , "2023.12.05에\n19:20 ~ 21:00까지 사용", "2023.12.06에\n09:10 ~ 11:00까지 사용", "노트북 충전기 1개가\n새로 들어왔습니다.",
            "2023.12.07에\n09:30 ~ 10:00까지 사용", "2023.12.08에\n09:50 ~ 11:00까지 사용", "2023.12.11에\n17:37 ~ 19:04까지 사용",
            "노트북 충전기 1개가\n새로 들어왔습니다.", "2023.12.12에\n21:10 ~ 22:00까지 사용", "2023.12.13에\n12:22 ~ 14:00까지 사용")
        // 어댑터 초기화 및 설정

        // data.add("data test")
        adapter = AlarmAdapter(data)
        binding.recview.adapter = adapter

    }
}