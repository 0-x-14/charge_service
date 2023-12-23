package com.example.charge_service

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.charge_service.databinding.HomeUsingBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
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
            // 권한 허용이 안된 경우
            requestPermissions(PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            // 권한 허용이 설정된 경우
            initMapView()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_map_view, container, false)
        // savedInstanceState를 통해 이전 상태를 확인
        if (savedInstanceState == null) {
            // 이전 상태가 없으면 지도를 처음 생성
            mapView = rootView.findViewById(R.id.map)
            mapView.onCreate(savedInstanceState)
            initMapView()
        } else {
            // 이전 상태가 있다면, 해당 상태를 복원
            mapView = rootView.findViewById(R.id.map)
            mapView.onCreate(savedInstanceState)

            // 이전 상태를 복원하는 로직 추가
            mapView.onSaveInstanceState(savedInstanceState)
        }

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
        super.onViewCreated(view, savedInstanceState)
        mapView.onResume()
    }

    private fun initMapView() {
        val fm = requireActivity().supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }
        // fragment의 getMapAsync() 메서드로 OnMapReadyCallback 콜백을 등록하면 비동기로 NaverMap 객체를 얻을 수 있다.
        mapFragment.getMapAsync(this)

        if (isAdded) {
            locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        }

        Log.d("jupy", "locationSource is $locationSource")
    }

    // OnMapReadyCallback의 추상 메서드 구현
    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap

        // 위치 권한 체크
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

        naverMap.locationSource = locationSource
        //  naverMap.isMyLocationEnabled = true

        // Move the camera to Sookmyung Women's University Central Library
        val libraryLocation = LatLng(37.5455, 126.9648)
        val cameraUpdate = CameraUpdate.scrollTo(libraryLocation)
        naverMap.moveCamera(cameraUpdate)
    }

    // hasPermission()에서는 위치 권한이 있을 경우 true를, 없을 경우 false를 반환한다.
    private fun hasPermission(): Boolean {
        for (permission in PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(requireContext(), permission)
                != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("jupy", "Fragment onDestroy() is called")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    // 사용자가 설정한 위치를 기반으로 지도의 중심을 설정하는 메서드
    fun setMapLocation(latitude: Double, longitude: Double) {
        // 여기에 사용자가 설정한 위치를 기반으로 지도의 중심을 설정하는 코드를 추가
        naverMap?.moveCamera(CameraUpdate.scrollTo(LatLng(latitude, longitude)))
    }
}