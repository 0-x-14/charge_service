
import android.os.Bundle
import androidx.annotation.UiThread
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.charge_service.R
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback

class MapFragmentActivity : FragmentActivity(), OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_using)

        val fm: FragmentManager = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.commit { add(R.id.map, it) }
            }

        mapFragment.getMapAsync(this)
    }

    @UiThread
    override fun onMapReady(naverMap: NaverMap) {
        // 여기에 NaverMap이 준비되었을 때 실행되는 코드를 추가하세요.
        // 예: 초기 위치 설정, 마커 추가, 이벤트 처리 등
    }
}
