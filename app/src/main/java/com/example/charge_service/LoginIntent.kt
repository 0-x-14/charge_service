package com.example.charge_service

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.charge_service.databinding.LoginBinding

class LoginIntent : AppCompatActivity() {
    override fun onCreate(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.login)
        val binding = LoginBinding.inflate(layoutInflater)
        val editText = binding.editText1
        val button = binding.loginButton

        button.setOnClickListener {
            val id = editText.text.toString()

            // SharedPreferences에 ID를 저장
            val sharedPreferences: SharedPreferences = getSharedPreferences("id", MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("id", id)
            editor.apply()

            // 다음 액티비티를 시작합니다.
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}