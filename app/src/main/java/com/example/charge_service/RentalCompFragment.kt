package com.example.charge_service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlin.concurrent.timer

// timer의 위치가 xml에 설정해놓은대로 안 떠서 수정 필요함
// timer를 실행해놓고 다른 네비게이션 탭으로 이동하면 오류 발생. 수정 필요함

 class RentalCompFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.rental_comp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val time: TextView = view.findViewById(R.id.timer)
        // concurrent에서 timer를 사용하기 위해 timer 호출시 time 변수 이용
        val rental_comp_close: ImageView = view.findViewById(R.id.rental_comp_close)
        // 임시로 타이머가 작동되는지 확인하기 위해 close 버튼을 눌렀을 때 타이머가 실행되도록 함

        var hour = 0
        var minute = 0
        var second = 0
        // hour, minute, second는 남은 시간에 따라 바뀌므로 var로 설정함

        time.text = String.format("%02d : %02d : %02d", hour, minute, second)

        rental_comp_close.setOnClickListener{
            var timeTick = 7200
            // 2시간, 초 단위로 계산

            hour = timeTick / 3600

            if (hour == 2) {
                minute = 0
                second = 0
            } // 최대 대여 시간이 2시간이므로 hour==2인 경우 분과 초는 모두 0
            else if (hour == 1) {
                minute = (timeTick-3600) / 60
                second = (timeTick-3600) % 60
            } // 1시간 XX분 XX초일 경우 전체 시간에서 3600을 뺀 뒤 계산
            else {
                minute = timeTick / 60
                second = timeTick % 60
            }

            // concurrent를 이용해서 timer 설정
            timer(period = 1000) {
                // 1초마다 실행됨
                requireActivity().runOnUiThread {
                    time.text = String.format("%02d : %02d : %02d", hour, minute, second)
                } // 백그라운드에서도 타이머가 실행되도록 하기 위함
                if (hour == 0 && second == 0 && minute == 0) {
                    time.text = "사용 시간이 종료되었습니다. 반납해주세요"
                }
                if (minute == 0 && second == 0) {
                    hour--
                    minute = 60
                }
                if (second == 0) {
                    minute--
                    second = 60
                }
                second--
            }
        }
    }
}