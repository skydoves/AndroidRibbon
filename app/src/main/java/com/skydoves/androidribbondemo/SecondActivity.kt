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

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skydoves.androidribbon.RibbonView
import com.skydoves.androidribbon.ribbonView
import com.skydoves.androidribbondemo.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = ActivitySecondBinding.inflate(layoutInflater)
    setContentView(binding.root)

    with(binding) {
      ribbonRecyclerView.addRibbon(
        ribbonView = ribbonView(this@SecondActivity) {
          setText("item1")
          setTextColor(Color.WHITE)
          setRibbonBackgroundColorResource(R.color.colorPrimaryDark)
          setRibbonRadius(10f)
        }
      )
      ribbonRecyclerView.addRibbon(
        ribbonView = ribbonView(this@SecondActivity) {
          setText("item2")
          setTextColor(Color.WHITE)
          setRibbonBackgroundColorResource(R.color.purple)
        }
      )
      ribbonRecyclerView.addRibbon(
        ribbonView = ribbonView(this@SecondActivity) {
          setText("item3")
          setTextColor(Color.WHITE)
          setRibbonBackgroundColorResource(R.color.md_indigo_300)
        }
      )
      ribbonRecyclerView.addRibbon(
        ribbonView = RibbonView.Builder(this@SecondActivity)
          .setText("Item4")
          .setTextColor(Color.WHITE)
          .setRibbonBackgroundColorResource(R.color.md_blue_300)
          .build()
      )
    }
  }
}
