package com.example.charge_service

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

class HomeUsingFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.home_using, container, false)

        val mapViewFragment = MapViewFragment()

        val mapContainer = rootView.findViewById<FrameLayout>(R.id.mapContainer)
        if (mapContainer != null) {
            if (savedInstanceState == null) {
                childFragmentManager.beginTransaction()
                    .replace(mapContainer.id, mapViewFragment)
                    .commit()
            } else {
                // 앱이 다시 시작되었을 때 저장된 상태를 활용하여 지도의 위치를 설정
                val savedLatitude = savedInstanceState.getDouble("latitude", 37.5455)
                val savedLongitude = savedInstanceState.getDouble("longitude", 126.9648)
                mapViewFragment.setMapLocation(savedLatitude, savedLongitude)
            }
        } else {
            Log.e("jupy", "mapContainer is null")
        }

        return rootView
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // 여기에 현재 지도의 상태를 저장하는 코드를 추가
        // 예시로 사용자가 설정한 위치의 위도와 경도를 저장
        outState.putDouble("latitude", 37.5455 )
        outState.putDouble("longitude", 126.9648)
    }
}