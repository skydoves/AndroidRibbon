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
        ribbonRecyclerView.addRibbon(
            ribbonView = RibbonView.Builder(this)
                .setText("Item4")
                .setTextColor(Color.WHITE)
                .setRibbonBackgroundColor(ContextCompat.getColor(baseContext, R.color.md_blue_300))
                .build()
        )
    }
}
