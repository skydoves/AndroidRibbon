package com.skydoves.androidribbondemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Developed by skydoves on 2019-01-10.
 * Copyright (c) 2018 skydoves rights reserved.
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ribbonLayout.ribbonHeader = RibbonFactory.getChristmasRibbonHeader(this)
        ribbonLayout.ribbonBottom = RibbonFactory.getChristmasRibbonBottom(this)
        ribbonLayout01.ribbonHeader = RibbonFactory.getChristmasPinkRibbonHeader(this)
        ribbonLayout01.ribbonBottom = RibbonFactory.getChristmasPinkRibbonBottom(this)
        ribbonLayout02.ribbonHeader = RibbonFactory.getPresentRibbonHeader(this)
        ribbonLayout02.ribbonBottom = RibbonFactory.getPresentRibbonBottom(this)

        ribbonLayout.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
        ribbonLayout01.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
        ribbonLayout02.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}
