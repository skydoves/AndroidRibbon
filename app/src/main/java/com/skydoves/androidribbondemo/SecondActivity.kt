package com.skydoves.androidribbondemo

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.skydoves.androidribbon.RibbonView
import com.skydoves.androidribbon.ribbonView
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        ribbonRecyclerView.addRibbon(ribbonView = ribbonView(this) {
            text = "Item1"
            textColor = Color.WHITE
            ribbonBackgroundColor = ContextCompat.getColor(baseContext, R.color.colorPrimary)
        })
        ribbonRecyclerView.addRibbon(ribbonView = ribbonView(this) {
            text = "Item2"
            textColor = Color.WHITE
            ribbonBackgroundColor = ContextCompat.getColor(baseContext, R.color.purple)
        })
        ribbonRecyclerView.addRibbon(ribbonView = ribbonView(this) {
            text = "Item3"
            textColor = Color.WHITE
            ribbonBackgroundColor = ContextCompat.getColor(baseContext, R.color.md_indigo_300)
        })
        ribbonRecyclerView.addRibbon(ribbonView = RibbonView.Builder(this)
            .setText("Item4")
            .setTextColor(Color.WHITE)
            .setRibbonBackgroundColor(ContextCompat.getColor(baseContext, R.color.md_blue_300))
            .build())
    }
}
