/*
 * Copyright (C) 2019 skydoves
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
