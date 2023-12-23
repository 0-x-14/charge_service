//package com.example.charge_service
//
//import android.Manifest
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import androidx.fragment.app.FragmentManager
//import com.naver.maps.map.LocationTrackingMode
//import com.naver.maps.map.MapFragment
//import com.naver.maps.map.NaverMap
//import com.naver.maps.map.OnMapReadyCallback
//import com.naver.maps.map.util.FusedLocationSource
//
//class MapViewFragmentback : AppCompatActivity(), OnMapReadyCallback {
//
//    private var naverMap: NaverMap? = null
//    private var locationSource: FusedLocationSource? = null
//
//    companion object {
//        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
//        private val PERMISSIONS = arrayOf(
//            Manifest.permission.ACCESS_FINE_LOCATION,
//            Manifest.permission.ACCESS_COARSE_LOCATION
//        )
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.home_using)
//
//        val fm: FragmentManager = supportFragmentManager
//        val mapFragment = fm.findFragmentById(R.id.map_fragment) as MapFragment?
//            ?: MapFragment.newInstance().also {
//                fm.beginTransaction().add(R.id.map_fragment, it).commit()
//            }
//
//        mapFragment.getMapAsync(this)
//        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
//    }
//
//    override fun onMapReady(naverMap: NaverMap) {
//        this.naverMap = naverMap
//        naverMap.locationSource = locationSource // 현재 위치 표시
//        ActivityCompat.requestPermissions(this, PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE)
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        grantResults: IntArray
//    ) {
//        if (locationSource?.onRequestPermissionsResult(requestCode, permissions, grantResults) == true) {
//            if (!locationSource?.isActivated!!) {
//                naverMap?.locationTrackingMode = LocationTrackingMode.None
//            } else {
//                naverMap?.locationTrackingMode = LocationTrackingMode.Follow
//            }
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }
//}