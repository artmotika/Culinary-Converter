package com.example.converterapp


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.converterapp.adapter.VolumeAdapter
import com.example.converterapp.model.PageItem

class VolumeSelection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volume_selection)
        setVolumeRecycler(volumeArray)
    }

    private fun setVolumeRecycler(pageArray: Array<PageItem>) {
        val layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        val volumeRecycler: RecyclerView = findViewById(R.id.volumeRecycler)
        volumeRecycler.layoutManager = layoutManager
        volumeRecycler.adapter = VolumeAdapter(this, pageArray)
    }
}