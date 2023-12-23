package com.example.charge_service

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.charge_service.databinding.HomeUsingBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource

class MapViewFragment : Fragment(), OnMapReadyCallback {
    private val LOCATION_PERMISSION_REQUEST_CODE = 5000
    private lateinit var mapView: MapView

    private val PERMISSIONS = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

    private lateinit var binding: HomeUsingBinding
    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource

    // onCreate에서 권한을 확인하며 위치 권한이 없을 경우 사용자에게 권한을 요청한다.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!hasPermission()) {
            Log.d("jupy", "에러 확인 -0")
            // 권한 허용이 안된 경우
            requestPermissions(PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE)
            Log.d("jupy", "에러 확인 -1")
        } else {
            // 권한 허용이 설정된 경우
            Log.d("jupy", "에러 확인 -2")
            initMapView()
            Log.d("jupy", "에러 확인 -3")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("jupy", "에러 확인2 -1")
        val rootView = inflater.inflate(R.layout.fragment_map_view, container, false)
        Log.d("jupy", "에러 확인2 -2")
        // savedInstanceState를 통해 이전 상태를 확인
        if (savedInstanceState == null) {
            Log.d("jupy", "에러 확인2 -3")
            // 이전 상태가 없으면 지도를 처음 생성
            mapView = rootView.findViewById(R.id.map)
            Log.d("jupy", "에러 확인2 -4")
            mapView.onCreate(savedInstanceState)
            Log.d("jupy", "에러 확인2 -5")
            initMapView()
            Log.d("jupy", "에러 확인2 -6")
        } else {
            Log.d("jupy", "에러 확인2 -7")
            // 이전 상태가 있다면, 해당 상태를 복원
            mapView = rootView.findViewById(R.id.map)
            Log.d("jupy", "에러 확인2 -8")
            mapView.onCreate(savedInstanceState)
            Log.d("jupy", "에러 확인2 -9")

            // 이전 상태를 복원하는 로직 추가
            mapView.onSaveInstanceState(savedInstanceState)
            Log.d("jupy", "에러 확인2 -10")
        }

        Log.d("jupy", "에러 확인2 -11")

        return rootView
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("jupy", "Fragment onViewStateRestored() is called")

        // savedInstanceState를 통해 이전 상태를 확인하고 필요한 복원 작업을 수행
        if (savedInstanceState != null) {
            // 이전 상태를 복원하는 로직 추가
            val savedLatitude = savedInstanceState.getDouble("latitude", 37.5455)
            val savedLongitude = savedInstanceState.getDouble("longitude", 126.9648)
            setMapLocation(savedLatitude, savedLongitude)
        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("jupy", "에러 확인 -8")
        super.onViewCreated(view, savedInstanceState)
        Log.d("jupy", "에러 확인 -9")
        mapView.onResume()
        Log.d("jupy", "에러 확인 -10")
    }

    private fun initMapView() {
        Log.d("jupy", "에러 확인 -11")
        val fm = requireActivity().supportFragmentManager
        Log.d("jupy", "에러 확인 -12")
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                Log.d("jupy", "에러 확인 -13")
                fm.beginTransaction().add(R.id.map, it).commit()
            }
        Log.d("jupy", "에러 확인 -14")
        // fragment의 getMapAsync() 메서드로 OnMapReadyCallback 콜백을 등록하면 비동기로 NaverMap 객체를 얻을 수 있다.
        mapFragment.getMapAsync(this)
        Log.d("jupy", "에러 확인 -15")

        if (isAdded) {
            Log.d("jupy", "에러 확인 -16")
            locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
            Log.d("jupy", "if문이 실행되었습니다.")
        }

        Log.d("jupy", "locationSource is $locationSource")
    }

    // OnMapReadyCallback의 추상 메서드 구현
    override fun onMapReady(naverMap: NaverMap) {
        Log.d("jupy", "ㅅㅂ -1")
        this.naverMap = naverMap

        Log.d("jupy", "ㅅㅂ -2")
        // 위치 권한 체크
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("jupy", "ㅅㅂ -3")
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            Log.d("jupy", "ㅅㅂ -4")
            return
        }
        Log.d("jupy", "ㅅㅂ -5")

        naverMap.locationSource = locationSource
        Log.d("jupy", "ㅅㅂ -6")
        //  naverMap.isMyLocationEnabled = true

        Log.d("jupy", "ㅅㅂ -7")
        // Move the camera to Sookmyung Women's University Central Library
        val libraryLocation = LatLng(37.5455, 126.9648)
        Log.d("jupy", "ㅅㅂ -8")
        val cameraUpdate = CameraUpdate.scrollTo(libraryLocation)
        Log.d("jupy", "ㅅㅂ -9")
        naverMap.moveCamera(cameraUpdate)
        Log.d("jupy", "ㅅㅂ -10")
    }

//        try {
//
//            Log.d("jupy", "에러 확인 -12")
//            this.naverMap = naverMap
//            // 현재 위치
//            if (naverMap == null) {
//                Log.e("jupy", "에러 확인 -12 : naverMap 객체가 null입니다.")
//                return
//            }
//            Log.d("jupy", "naverMap의 상태: $naverMap")
//            Log.d("jupy", "에러 확인 -13")
//            naverMap.locationSource = locationSource
//            // 현재 위치 버튼 기능
//            Log.d("jupy", "에러 확인 -14")
//            naverMap.uiSettings.isLocationButtonEnabled = true
//            // 위치를 추적하면서 카메라도 따라 움직인다.
//            Log.d("jupy", "에러 확인 -15")
//            naverMap.locationTrackingMode = LocationTrackingMode.Follow
//            Log.d("jupy", "에러 확인 -16")
//        } catch (e: RuntimeException) {
//            Log.e("jupy", "locationTrackingMode 설정 중 예외 발생: ${e.message}")
//        } catch (e: Exception) {
//            Log.e("jupy", "locationTrackingMode 설정 중 예외 발생: ${e.message}")
//        }
    // }

    // hasPermission()에서는 위치 권한이 있을 경우 true를, 없을 경우 false를 반환한다.
    private fun hasPermission(): Boolean {
        Log.d("jupy", "에러 확인 -17")
        for (permission in PERMISSIONS) {
            Log.d("jupy", "에러 확인 -18")
            if (ContextCompat.checkSelfPermission(requireContext(), permission)
                != PackageManager.PERMISSION_GRANTED
            ) {
                Log.d("jupy", "에러 확인 -19")
                return false
            }
        }
        Log.d("jupy", "에러 확인 -20")
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("jupy", "진짜개짜증남")
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("jupy", "onActivityResult - requestCode: $requestCode, resultCode: $resultCode")
    }

    override fun onStart() {
        Log.d("jupy", "에러 확인 -27")
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        Log.d("jupy", "에러 확인 -28")
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        Log.d("jupy", "에러 확인 -29")
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        Log.d("jupy", "에러 확인 -30")
        super.onStop()
        mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("jupy", "에러 확인 -31")
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        Log.d("jupy", "에러 확인 -32")
        super.onDestroyView()
        mapView.onDestroy()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("jupy", "Fragment onDestroy() is called")
    }

    override fun onLowMemory() {
        Log.d("jupy", "에러 확인 -33")
        super.onLowMemory()
        mapView.onLowMemory()
    }

    // 사용자가 설정한 위치를 기반으로 지도의 중심을 설정하는 메서드
    fun setMapLocation(latitude: Double, longitude: Double) {
        Log.d("jupy", "에러 확인 -34")
        // 여기에 사용자가 설정한 위치를 기반으로 지도의 중심을 설정하는 코드를 추가
        naverMap?.moveCamera(CameraUpdate.scrollTo(LatLng(latitude, longitude)))
    }
}