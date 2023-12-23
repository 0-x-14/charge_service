package com.example.charge_service

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.charge_service.databinding.AlarmDbBinding


class AlarmAdapter(private val dataList: List<String>) :
    RecyclerView.Adapter<AlarmAdapter.ViewHolder>() {

    class ViewHolder(val binding: AlarmDbBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AlarmDbBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        val bd = holder.binding

        // Assuming 'record' is the ID of the TextView in alarm_db.xml
        bd.record.text = data
    }

    override fun getItemCount(): Int = dataList.size
}