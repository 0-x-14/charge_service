
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import com.example.charge_service.HomeActivity
import com.example.charge_service.R
import com.google.firebase.database.FirebaseDatabase
import com.google.zxing.integration.android.IntentIntegrator
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val initialAvailableCount1 = "3"
private val initialAvailableCount2 = "3"
private val initialAvailableCount3 = "3"

class RentalFragment : Fragment() {
    private lateinit var database: FirebaseDatabase
    private lateinit var rentalButton1: Button
    private lateinit var rentalButton2: Button
    private lateinit var rentalButton3: Button
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var textView3: TextView
    private val NOTIFICATION_CHANNEL_ID = "rental_notification_channel"
    private val channelName = "충전하겠숙?"
    private val description = "대여가 완료되었습니다. 2시간 뒤에 반납해주세요."
    private val importance = NotificationManager.IMPORTANCE_HIGH

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.rental, container, false)
        database = FirebaseDatabase.getInstance()

        val toggle8 = view.findViewById<Switch>(R.id.eightpin_toggle)
        val toggleC = view.findViewById<Switch>(R.id.eightpin_toggle2)
        val toggleNote = view.findViewById<Switch>(R.id.eightpin_toggle3)

        val eightPin = view.findViewById<TextView>(R.id.numOfEightPin)
        val cPin = view.findViewById<TextView>(R.id.numOfCtype)
        val notebook = view.findViewById<TextView>(R.id.numOfnote)

        rentalButton1 = view.findViewById(R.id.rentalButton1)
        rentalButton2 = view.findViewById(R.id.rentalButton2)
        rentalButton3 = view.findViewById(R.id.rentalButton3)
        textView1 = view.findViewById(R.id.numOfEightPin)
        textView2 = view.findViewById(R.id.numOfCtype)
        textView3 = view.findViewById(R.id.numOfnote)

        textView1.text = getFromSharedPreferences("numOfEightPin", initialAvailableCount1)
        textView2.text = getFromSharedPreferences("numOfCtype", initialAvailableCount2)
        textView3.text = getFromSharedPreferences("numOfnote", initialAvailableCount3)

        rentalButton1.setOnClickListener {
            startQRScanner(1)
            if (textView1.text.toString().toInt() > 0) {
                textView1.text = (textView1.text.toString().toInt() - 1).toString()
                saveToSharedPreferences("numOfEightPin", textView1.text.toString())
                showNotification()
            }
            else {
                Toast.makeText(requireContext(), "8핀 충전기가 모두 대여되었습니다.", Toast.LENGTH_LONG).show()
            }
        }

        rentalButton2.setOnClickListener {
            startQRScanner(2)
            if (textView2.text.toString().toInt() > 0) {
                textView2.text = (textView2.text.toString().toInt() - 1).toString()
                saveToSharedPreferences("numOfCtype", textView2.text.toString())
                showNotification()
            }
            else {
                Toast.makeText(requireContext(), "C타입 충전기가 모두 대여되었습니다.", Toast.LENGTH_LONG).show()
            }
        }

        rentalButton3.setOnClickListener {
            startQRScanner(3)
            if (textView3.text.toString().toInt() > 0) {
                textView3.text = (textView3.text.toString().toInt() - 1).toString()
                saveToSharedPreferences("numOfnote", textView3.text.toString())
                showNotification()
            }
            else {
                Toast.makeText(requireContext(), "노트북 충전기가 모두 대여되었습니다.", Toast.LENGTH_LONG).show()
            }
        }
        return view

    }
    private fun showNotification() {
        // 1. Notification Channel 생성 (API Level 26 이상)
        createNotificationChannel()

        // 2. Notification Builder 사용
        val builder = NotificationCompat.Builder(requireContext(), NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.alarm_img)
            .setContentTitle(channelName)
            .setContentText(description)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        // 3. Notification Manager로 Notification 표시
        val notificationManager =
            requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(1234, builder.build())
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                channelName,
                importance
            ).apply {
                description = this@RentalFragment.description
                enableLights(true)
            }

            val notificationManager =
                requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }


    private fun startQRScanner(buttonId: Int) {
        val integrator = IntentIntegrator.forSupportFragment(this)
        integrator.setOrientationLocked(false)
        integrator.initiateScan()
        integrator.setRequestCode(buttonId)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == AppCompatActivity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(requireContext(), "취소됨", Toast.LENGTH_SHORT).show()
                } else {
                    when (requestCode) {
                        1 -> {
                            Toast.makeText(
                                requireContext(),
                                "8핀 충전기 대여가 완료되었습니다.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        2 -> {
                            Toast.makeText(
                                requireContext(),
                                "C타입 충전기 대여가 완료되었습니다.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        3 -> {
                            Toast.makeText(
                                requireContext(),
                                "노트북 충전기 대여가 완료되었습니다.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    saveCurrentTimeToFirebase()
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }


    private fun saveToSharedPreferences(key: String, value: String) {
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun getFromSharedPreferences(key: String, defaultValue: String): String {
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", AppCompatActivity.MODE_PRIVATE)
        val defaultValueAfterLogout = "3"
        val storedValue = sharedPreferences.getString(key, defaultValueAfterLogout)
        return storedValue ?: defaultValueAfterLogout
    }

    private fun saveCurrentTimeToFirebase() {
        val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val timeRef = database.getReference("rental_time").push()
        timeRef.setValue(currentTime)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "대여 시간이 성공적으로 저장되었습니다.", Toast.LENGTH_SHORT).show()
                (activity as? HomeActivity)?.switchToRentalCompFragment()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "시간을 저장하는 데 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
    }
    fun requestSharedPreferencesReset() {
        (activity as? HomeActivity)?.resetSharedPreferences()
    }
}