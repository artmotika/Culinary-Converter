package com.example.converterapp.adapter

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.converterapp.R
import com.example.converterapp.VolumeSetting
import com.example.converterapp.model.PageItem
import com.example.converterapp.volumeRussianTextArray

class VolumeAdapter(private var context: Context, private var pages: Array<PageItem>) :
    RecyclerView.Adapter<VolumeAdapter.VolumeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, i: Int): VolumeViewHolder {
        val volumeItems = LayoutInflater.from(context).inflate(R.layout.volume_item, parent, false)
        return VolumeViewHolder(volumeItems)
    }

    override fun onBindViewHolder(volumeViewHolder: VolumeViewHolder, i: Int) {
        volumeViewHolder.textVolume.text = pages[i].title
        volumeViewHolder.textVolumeRus.text = volumeRussianTextArray[i]
        volumeViewHolder.imageVolume.setImageResource(pages[i].img)

        volumeViewHolder.imageVolume.setOnClickListener {
            val intent = Intent(context , VolumeSetting::class.java)
            intent.putExtra("volumeId", i)
            context.startActivity(intent)
        }
    }

    class VolumeViewHolder(itemView: View) : ViewHolder(itemView) {
        var textVolumeRus: TextView = itemView.findViewById(R.id.textVolumeRus)
        var textVolume: TextView = itemView.findViewById(R.id.textVolume)
        var imageVolume: ImageButton = itemView.findViewById(R.id.imageVolume)
    }

    override fun getItemCount(): Int {
        return pages.size
    }
}