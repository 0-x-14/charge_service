package com.example.charge_service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import com.example.charge_service.MainActivity.Companion.preferences
import com.example.charge_service.databinding.ReturnComp4Binding
import com.example.charge_service.databinding.ReturnPageBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReturnFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReturnFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        // return page에 대한 xml
        val binding = ReturnPageBinding.inflate(layoutInflater)

//        val userName = findViewById<TextView>(R.id.textView)
//        val id = preferences.getString("id", "")
//        userName.text = "${id} 님 안녕하세요"
        // 눈송이를 id값으로 변경

        // 메뉴와 알람 누르면 이벤트 처리
        binding.menuBtn.setOnClickListener{
            binding.homeLayout.openDrawer(GravityCompat.END)
        }

        binding.alarmBtn.setOnClickListener{

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.return_page, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReturnFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReturnFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}