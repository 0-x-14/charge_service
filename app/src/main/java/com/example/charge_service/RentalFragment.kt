import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.fragment.app.Fragment
import com.example.charge_service.QRScanActivity
import com.example.charge_service.R

class RentalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.rental, container, false)

        // Button 1 Click Listener
        val btnScan1: Button = view.findViewById(R.id.rentalButton1)
        btnScan1.setOnClickListener {
            startQRScannerForType("8핀", 1)
        }

        // Button 2 Click Listener
        val btnScan2: Button = view.findViewById(R.id.rentalButton2)
        btnScan2.setOnClickListener {
            startQRScannerForType("C타입", 2)
        }

        // Button 3 Click Listener
        val btnScan3: Button = view.findViewById(R.id.rentalButton3)
        btnScan3.setOnClickListener {
            startQRScannerForType("노트", 3)
        }

        return view
    }

    private fun startQRScannerForType(type: String, requestCode: Int) {
        val intent = Intent(activity, QRScanActivity::class.java)
        intent.putExtra("TYPE", type)
        startActivityForResult(intent, requestCode)
    }

    // Add onActivityResult here if needed
}
