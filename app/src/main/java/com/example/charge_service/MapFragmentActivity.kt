//
//import android.content.pm.PackageManager
//import android.os.Bundle
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.FragmentActivity
//import com.example.charge_service.R
//import com.naver.maps.map.LocationTrackingMode
//import com.naver.maps.map.MapFragment
//import com.naver.maps.map.NaverMap
//import com.naver.maps.map.OnMapReadyCallback
//import com.naver.maps.map.util.FusedLocationSource
//
//class MapFragmentActivity : FragmentActivity(), OnMapReadyCallback {
//
//    private val LOCATION_PERMISSION_REQUEST_CODE = 1000 // 위치 권한 요청 코드
//    private lateinit var naverMap: NaverMap
//    private lateinit var locationSource: FusedLocationSource
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.home_using)
//        // 위치 권한이 없다면 권한 요청
//        if (!hasLocationPermission()) {
//            requestLocationPermission()
//        } else {
//            initMap()
//        }
//    }
//
//    private fun initMap() {
//        val fm = supportFragmentManager
//        val mapFragment = fm.findFragmentById(R.id.mapContainer) as MapFragment?
//            ?: MapFragment.newInstance().also {
//                fm.beginTransaction().add(R.id.mapContainer, it).commit()
//            }
//
//        mapFragment.getMapAsync(this)
//        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
//    }
//
//    private fun hasLocationPermission(): Boolean {
//        return ContextCompat.checkSelfPermission(
//            this,
//            android.Manifest.permission.ACCESS_FINE_LOCATION
//        ) == PackageManager.PERMISSION_GRANTED
//    }
//
//    private fun requestLocationPermission() {
//        ActivityCompat.requestPermissions(
//            this,
//            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
//            LOCATION_PERMISSION_REQUEST_CODE
//        )
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                initMap()
//            } else {
//                // 위치 권한이 거부된 경우 처리
//                // 사용자에게 권한이 필요함을 알리는 메시지 등을 표시할 수 있습니다.
//            }
//        }
//    }
//
//    override fun onMapReady(naverMap: NaverMap) {
//        this.naverMap = naverMap
//        naverMap.locationSource = locationSource
//        naverMap.uiSettings.isLocationButtonEnabled = true
//        naverMap.locationTrackingMode = LocationTrackingMode.Follow
//
//    }
//
//}
