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

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.androidribbon.annotations.Dp

/** RibbonRecyclerView helps to create [RecyclerView] that has [RibbonView] as items. */
class RibbonRecyclerView : RecyclerView, IRibbonList {

  @Dp private var space: Float = 3f
  private var orientation: Int = HORIZONTAL

  private val ribbonAdapter: RibbonRecyclerAdapter = RibbonRecyclerAdapter()

  constructor(context: Context) : super(context) {
    onCreate()
  }

  constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    getAttrs(attrs)
    onCreate()
  }

  constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
    context,
    attrs,
    defStyleAttr
  ) {
    getAttrs(attrs, defStyleAttr)
    onCreate()
  }

  private fun getAttrs(attributeSet: AttributeSet) {
    val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.RibbonRecyclerView)
    try {
      setTypeArray(typedArray)
    } finally {
      typedArray.recycle()
    }
  }

  private fun getAttrs(attributeSet: AttributeSet, defStyleAttr: Int) {
    val typedArray =
      context.obtainStyledAttributes(attributeSet, R.styleable.RibbonRecyclerView, defStyleAttr, 0)
    try {
      setTypeArray(typedArray)
    } finally {
      typedArray.recycle()
    }
  }

  private fun setTypeArray(typeArray: TypedArray) {
    space = typeArray.getDimension(R.styleable.RibbonRecyclerView_ribbon_recycler_space, space)
    orientation =
      typeArray.getInt(R.styleable.RibbonRecyclerView_ribbon_recycler_orientation, orientation)
  }

  private fun onCreate() {
    layoutManager = LinearLayoutManager(context, orientation, false)
    addItemDecoration(RibbonTagItemDecoration(space.dp2px, orientation))
    adapter = ribbonAdapter
  }

  /** add a ribbon on the adapter. */
  override fun addRibbon(ribbonView: RibbonView) =
    ribbonAdapter.addRibbon(ribbonView)

  /** add a ribbon on the adapter by position. */
  override fun addRibbon(position: Int, ribbonView: RibbonView) =
    ribbonAdapter.addRibbon(position, ribbonView)

  /** add ribbons on the adapter. */
  override fun addRibbonList(ribbonViewList: List<RibbonView>) =
    ribbonAdapter.addRibbonList(ribbonViewList)

  /** get a ribbon from the adapter. */
  override fun getRibbonView(position: Int): RibbonView =
    ribbonAdapter.getRibbonView(position)

  /** add ribbon from the adapter. */
  override fun getRibbonViews(): List<RibbonView> =
    ribbonAdapter.getRibbonViews()

  /** remove a ribbon on the adapter. */
  override fun removeRibbon(ribbonView: RibbonView) =
    ribbonAdapter.removeRibbon(ribbonView)

  /** remove a ribbon on the adapter by position. */
  override fun removeRibbon(position: Int) =
    ribbonAdapter.removeRibbon(position)

  /** clear adapter. */
  override fun clear() = ribbonAdapter.clear()
}
