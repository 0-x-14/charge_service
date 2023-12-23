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
        Log.d("jupy", "ㅜㅜ -1")
        if (mapContainer != null) {
            Log.d("jupy", "mapContainer is $mapContainer")
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
        // 클래스 멤버로 선언
//        val mapViewFragment = MapViewFragment()
//
//// onCreateView 메서드에서 사용
//        val mapContainer = rootView.findViewById<FrameLayout>(R.id.mapContainer)
//        val existingFragment = childFragmentManager.findFragmentById(mapContainer.id)
//        if (existingFragment == null) {
//            childFragmentManager.beginTransaction()
//                .replace(mapContainer.id, mapViewFragment)
//                .commit()
//        }

        return rootView
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // 여기에 현재 지도의 상태를 저장하는 코드를 추가
        // 예시로 사용자가 설정한 위치의 위도와 경도를 저장
        outState.putDouble("latitude", 37.5455 )
        outState.putDouble("longitude", 126.9648)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment?
//        mapFragment?.getMapAsync(this)
//    }
}