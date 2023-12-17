package com.example.charge_service

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment(val activity: Activity) : Fragment(), OnMapReadyCallback {
    lateinit var locationPermission: ActivityResultLauncher<Array<String>>

    private lateinit var gMap: GoogleMap

    lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var locationCallback: LocationCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        locationPermission = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { results ->
            if (results.all { it.value }) {
                updateLocation()
                // 권한이 모두 허용된 경우에만 위치 정보를 가져오는 함수 호출
            }
            else {
                Toast.makeText(activity, "권한 승인이 필요합니다.", Toast.LENGTH_SHORT).show()
                // 권한이 하나라도 거부된 경우 경우 toast 메세지 출력
            }
        }

        locationPermission.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        gMap = googleMap
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        updateLocation() // 현재 위치 정보를 계속해서 불러오는 함수
    }

    fun updateLocation() {
        // 필요한 위치 권한이 부여되었는지 확인
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val locationRequest = LocationRequest.create().apply {
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                interval = 1000
            }

            locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult?) {
                    locationResult?.let {
                        for (location in it.locations) {
                            Log.d("jupy", "현재 위치 위도 ${location.latitude} 경도 ${location.longitude}")
                            setLastLocation(location.latitude, location.longitude)
                            // 로그창에 위도 및 경도 출력, setLastLocation 호출로 최신 위치 갱신
                        }
                    }
                }
            }
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
        } else {
            // 권한이 없는 경우 사용자에게 권한을 요청
            requestLocationPermission()
        }
    }
    private fun requestLocationPermission() {
        locationPermission.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
    }
    fun setLastLocation(latitude:Double, longitude:Double) {
        val LATLNG = LatLng(latitude, longitude)

        val makerOptions = MarkerOptions().position(LATLNG).title("현재 위치")

        val cameraPosition = CameraPosition.Builder().target(LATLNG).zoom(15.0f).build()

        gMap.clear()
        gMap.addMarker(makerOptions)
        gMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        // 현재 위치 표시
    }
}