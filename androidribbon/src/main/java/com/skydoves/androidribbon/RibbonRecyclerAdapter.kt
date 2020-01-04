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

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

/** RibbonRecyclerAdapter is an implementation of [RecyclerView.Adapter] that has [RibbonView] as items. */
class RibbonRecyclerAdapter :
  RecyclerView.Adapter<RibbonRecyclerViewHolder>(), IRibbonList {

  private val ribbonViewList: MutableList<RibbonView> = mutableListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RibbonRecyclerViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return RibbonRecyclerViewHolder(inflater.inflate(R.layout.item_ribbon, parent, false))
  }

  override fun onBindViewHolder(holder: RibbonRecyclerViewHolder, position: Int) {
    holder.itemView.findViewById<LinearLayout>(R.id.item_ribbon_layout).apply {
      removeAllViews()
      addView(ribbonViewList[position])
    }
  }

  /** adds a ribbon on the adapter. */
  override fun addRibbon(ribbonView: RibbonView) {
    this.ribbonViewList.add(ribbonView)
    notifyDataSetChanged()
  }

  /** adds a ribbon on the adapter by position. */
  override fun addRibbon(position: Int, ribbonView: RibbonView) {
    this.ribbonViewList.add(position, ribbonView)
    notifyItemChanged(position)
  }

  /** adds ribbons on the adapter. */
  override fun addRibbonList(ribbonViewList: List<RibbonView>) {
    this.ribbonViewList.addAll(ribbonViewList)
    notifyDataSetChanged()
  }

  /** gets a ribbon from the adapter. */
  override fun getRibbonView(position: Int): RibbonView = this.ribbonViewList[position]

  /** gets a list of [RibbonView] from the adapter. */
  override fun getRibbonViews(): List<RibbonView> = this.ribbonViewList

  /** gets a size of [RibbonView] from the adapter. */
  override fun getItemCount() = this.ribbonViewList.size

  /** removes a ribbon on the adapter. */
  override fun removeRibbon(ribbonView: RibbonView) {
    this.ribbonViewList.remove(ribbonView)
    notifyDataSetChanged()
  }

  /** removes a ribbon on the adapter by position. */
  override fun removeRibbon(position: Int) {
    this.ribbonViewList.removeAt(position)
    notifyItemChanged(position)
  }

  /** clears all of [RibbonView] items. */
  override fun clear() {
    this.ribbonViewList.clear()
    notifyDataSetChanged()
  }
}
