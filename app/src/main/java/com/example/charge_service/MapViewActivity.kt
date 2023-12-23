//
//import android.content.pm.PackageManager
//import android.location.Geocoder
//import android.os.Build
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import com.example.charge_service.R
//import com.naver.maps.geometry.LatLng
//import com.naver.maps.map.LocationTrackingMode
//import com.naver.maps.map.MapFragment
//import com.naver.maps.map.NaverMap
//import com.naver.maps.map.OnMapReadyCallback
//import com.naver.maps.map.overlay.Marker
//import com.naver.maps.map.util.FusedLocationSource
//import java.util.Locale
//
//class MapViewActivity : AppCompatActivity(), OnMapReadyCallback {
//    private val LOCATION_PERMISSION_REQUEST_CODE = 5000
//
//    private val PERMISSIONS = arrayOf(
//        android.Manifest.permission.ACCESS_FINE_LOCATION,
//        android.Manifest.permission.ACCESS_COARSE_LOCATION
//    )
//
//
//    private lateinit var naverMap: NaverMap
//    private lateinit var locationSource: FusedLocationSource
//    private val marker = Marker()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.home_using)
//
//        if (!hasPermission()) {
//            ActivityCompat.requestPermissions(this, PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE)
//        } else {
//            initMapView()
//        }
//    }
//
//    private fun initMapView() {
//        val fm = supportFragmentManager
//        val mapFragment = fm.findFragmentById(R.id.map_fragment) as MapFragment?
//            ?: MapFragment.newInstance().also {
//                fm.beginTransaction().add(R.id.map_fragment, it).commit()
//            }
//
//        mapFragment.getMapAsync(this)
//        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
//    }
//
//    private fun hasPermission(): Boolean {
//        for (permission in PERMISSIONS) {
//            if (ContextCompat.checkSelfPermission(this, permission)
//                != PackageManager.PERMISSION_GRANTED
//            ) {
//                return false
//            }
//        }
//        return true
//    }
//
//    override fun onMapReady(naverMap: NaverMap) {
//        this.naverMap = naverMap
//        naverMap.locationSource = locationSource
//        naverMap.uiSettings.isLocationButtonEnabled = true
//        naverMap.locationTrackingMode = LocationTrackingMode.Follow
//
//        naverMap.setOnMapClickListener { point, coord ->
//            marker.position = LatLng(coord.latitude, coord.longitude)
//            marker.map = naverMap
//            getAddress(coord.latitude, coord.longitude)
//        }
//    }
//
//    private fun getAddress(latitude: Double, longitude: Double) {
//        val geocoder = Geocoder(applicationContext, Locale.KOREAN)
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            geocoder.getFromLocation(
//                latitude, longitude, 1
//            ) { address ->
//                if (address.size != 0) {
//                    toast(address[0].getAddressLine(0))
//                }
//            }
//        } else {
//            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
//            if (addresses != null) {
//                toast(addresses[0].getAddressLine(0))
//            }
//        }
//    }
//
//    private fun toast(text: String) {
//        runOnUiThread {
//            Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
//        }
//    }
//}
