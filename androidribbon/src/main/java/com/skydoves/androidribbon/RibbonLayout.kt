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
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.skydoves.androidribbon.internal.viewProperty

/**
 * RibbonLayout has header and bottom align ribbons.
 * They will overlap on the top with other complex child views.
 */
@Suppress("MemberVisibilityCanBePrivate", "unused")
class RibbonLayout : FrameLayout, RibbonInterface {

  var ribbonHeaderAlign: Int by viewProperty(Gravity.START)

  var ribbonBottomAlign: Int by viewProperty(Gravity.CENTER)

  var ribbonHeader: RibbonInterface by viewProperty(RibbonView(context))

  var ribbonBottom: RibbonInterface by viewProperty(RibbonView(context))

  constructor(context: Context) : super(context)

  constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    getAttrs(attrs)
  }

  constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
    context,
    attrs,
    defStyleAttr
  ) {
    getAttrs(attrs, defStyleAttr)
  }

  private fun getAttrs(attributeSet: AttributeSet) {
    val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.RibbonLayout)
    try {
      setTypeArray(typedArray)
    } finally {
      typedArray.recycle()
    }
  }

  private fun getAttrs(attributeSet: AttributeSet, defStyleAttr: Int) {
    val typedArray =
      context.obtainStyledAttributes(attributeSet, R.styleable.RibbonLayout, defStyleAttr, 0)
    try {
      setTypeArray(typedArray)
    } finally {
      typedArray.recycle()
    }
  }

  private fun setTypeArray(typeArray: TypedArray) {
    this.ribbonHeaderAlign =
      typeArray.getInteger(R.styleable.RibbonLayout_ribbonLayout_header_align, ribbonHeaderAlign)
    this.ribbonBottomAlign =
      typeArray.getInteger(R.styleable.RibbonLayout_ribbonLayout_bottom_align, ribbonBottomAlign)
  }

  /** update [RibbonLayout] after finishing inflate. */
  override fun onFinishInflate() {
    super.onFinishInflate()
    updateRibbon()
  }

  /** update [RibbonLayout] by attributes. */
  override fun updateRibbon() {
    val headerParams =
      LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    headerParams.gravity = ribbonHeaderAlign or Gravity.TOP
    addRibbonInterface(ribbonHeader, headerParams)

    val bottomParams =
      LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    bottomParams.gravity = ribbonBottomAlign or Gravity.BOTTOM
    addRibbonInterface(ribbonBottom, bottomParams)

    invalidate()
  }

  private fun addRibbonInterface(ribbonInterface: RibbonInterface, params: LayoutParams) {
    ribbonInterface.apply {
      if (this is RibbonView) {
        this.layoutParams = params
        addRibbonView(this)
      } else if (this is ShimmerRibbonView) {
        this.layoutParams = params
        addRibbonView(this)
      }
    }
  }

  private fun addRibbonView(view: View) {
    removeView(view)
    addView(view)
  }
}
