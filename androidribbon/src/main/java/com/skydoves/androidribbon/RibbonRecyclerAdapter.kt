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
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow

/** RibbonRecyclerAdapter is an implementation of [BaseAdapter] that has [RibbonView] as items. */
class RibbonRecyclerAdapter(private val delegate: RibbonRecyclerViewHolder.Delegate? = null) : BaseAdapter(), IRibbonList {

    init {
        addSection(ArrayList<RibbonView>())
    }

    /** add a ribbon on the adapter. */
    override fun addRibbon(ribbonView: RibbonView) {
        addItemOnSection(0, ribbonView)
        notifyDataSetChanged()
    }

    /** add a ribbon on the adapter by position. */
    override fun addRibbon(position: Int, ribbonView: RibbonView) {
        sections[0].add(position, ribbonView)
        notifyItemChanged(position)
    }

    /** add ribbons on the adapter. */
    override fun addRibbonList(ribbonViewList: List<RibbonView>) {
        addItemsOnSection(0, ribbonViewList)
        notifyDataSetChanged()
    }

    /** get a ribbon from the adapter. */
    override fun getRibbonView(position: Int): RibbonView {
        return sections[0][position] as RibbonView
    }

    /** add ribbon from the adapter. */
    override fun getRibbonViews(): List<RibbonView> {
        val ribbonList = ArrayList<RibbonView>()
        for (item in sections[0]) {
            if (item is RibbonView) {
                ribbonList.add(item)
            }
        }
        return ribbonList
    }

    /** remove a ribbon on the adapter. */
    override fun removeRibbon(ribbonView: RibbonView) {
        sections[0].remove(ribbonView)
        notifyDataSetChanged()
    }

    /** remove a ribbon on the adapter by position. */
    override fun removeRibbon(position: Int) {
        sections[0].removeAt(position)
        notifyItemChanged(position)
    }

    /** clear adapter. */
    override fun clear() {
        sections[0].clear()
        notifyDataSetChanged()
    }

    override fun layout(sectionRow: SectionRow): Int {
        return R.layout.item_ribbon
    }

    override fun viewHolder(layout: Int, view: View): BaseViewHolder {
        return RibbonRecyclerViewHolder(view, delegate)
    }
}
