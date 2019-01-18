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

package com.skydoves.androidribbon

import android.view.View
import android.widget.LinearLayout
import com.skydoves.baserecyclerviewadapter.BaseViewHolder

/** RibbonRecyclerAdapter is an implementation of [BaseViewHolder] that has [RibbonView] as data. */
@Suppress("CanBeParameter")
class RibbonRecyclerViewHolder(
    private val view: View,
    private val delegate: Delegate? = null
) : BaseViewHolder(view) {

    /** Delegate is delegate interface for communicating with view. */
    interface Delegate {
        /** onRibbonItemClick will invoked by [onClick] method. */
        fun onRibbonItemClick(ribbonView: RibbonView)
    }

    private lateinit var ribbonView: RibbonView

    /** bind data to RibbonView */
    override fun bindData(data: Any) {
        if (data is RibbonView) {
            this.ribbonView = data
            drawItemUI()
        }
    }

    /** draw on item UI */
    private fun drawItemUI() {
        val layout: LinearLayout = itemView.findViewById(R.id.item_ribbon_layout)
        layout.removeAllViews()
        layout.addView(ribbonView)
    }

    /** invoke onItemClickListener */
    override fun onClick(p0: View?) {
        delegate?.onRibbonItemClick(ribbonView)
    }

    /** invoke onItemLongClickListener */
    override fun onLongClick(p0: View?) = false
}
