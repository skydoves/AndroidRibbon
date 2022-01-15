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
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import com.skydoves.androidribbon.internal.viewProperty

/** create a [Shimmer] by [Shimmer.AlphaHighlightBuilder] using dsl. */
@Suppress("unused")
fun alphaShimmer(block: Shimmer.AlphaHighlightBuilder.() -> Unit): Shimmer =
  Shimmer.AlphaHighlightBuilder().apply(block).build()

/** create a [Shimmer] by [Shimmer.ColorHighlightBuilder] using dsl. */
@Suppress("unused")
fun colorShimmer(block: Shimmer.ColorHighlightBuilder.() -> Unit): Shimmer =
  Shimmer.ColorHighlightBuilder().apply(block).build()

/** create a [shimmerRibbonView] by [ShimmerRibbonView.Builder] using dsl. */
@Suppress("unused")
fun shimmerRibbonView(
  context: Context,
  block: ShimmerRibbonView.Builder.() -> Unit
): ShimmerRibbonView =
  ShimmerRibbonView.Builder(context).apply(block).build()

/** ShimmerRibbonView implements [RibbonView] on [ShimmerFrameLayout] for shimmering effect. */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class ShimmerRibbonView : ShimmerFrameLayout, RibbonInterface {

  var frameRotation = 0
    set(value) {
      field = value
      rotation(value)
    }

  var ribbon: RibbonView by viewProperty(RibbonView(context)) {
    frameRotation = ribbon.ribbonRotation
    ribbon.ribbonRotation = 0
  }

  var shimmer: Shimmer by viewProperty(
    Shimmer.AlphaHighlightBuilder()
      .setBaseAlpha(1.0f)
      .setHighlightAlpha(0.5f)
      .build()
  ) {
    setShimmer(it)
  }

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
    val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ShimmerRibbonView)
    try {
      setTypeArray(typedArray)
    } finally {
      typedArray.recycle()
    }
  }

  private fun getAttrs(attributeSet: AttributeSet, defStyleAttr: Int) {
    val typedArray =
      context.obtainStyledAttributes(attributeSet, R.styleable.ShimmerRibbonView, defStyleAttr, 0)
    try {
      setTypeArray(typedArray)
    } finally {
      typedArray.recycle()
    }
  }

  private fun setTypeArray(typeArray: TypedArray) {
    ribbon = ribbonView(context) {
      ribbonDrawable = typeArray.getDrawable(R.styleable.ShimmerRibbonView_shimmer_ribbon_drawable)
      ribbonBackgroundColor = typeArray.getColor(
        R.styleable.ShimmerRibbonView_shimmer_ribbon_background_color,
        ribbonBackgroundColor
      )
      ribbonRadius =
        typeArray.getDimension(
          R.styleable.ShimmerRibbonView_shimmer_ribbon_ribbonRadius,
          ribbonRadius
        )
      paddingLeft =
        typeArray.getDimension(
          R.styleable.ShimmerRibbonView_shimmer_ribbon_padding_left,
          paddingLeft
        )
      paddingTop =
        typeArray.getDimension(R.styleable.ShimmerRibbonView_shimmer_ribbon_padding_top, paddingTop)
      paddingRight =
        typeArray.getDimension(
          R.styleable.ShimmerRibbonView_shimmer_ribbon_padding_right,
          paddingRight
        )
      paddingBottom =
        typeArray.getDimension(
          R.styleable.ShimmerRibbonView_shimmer_ribbon_padding_bottom,
          paddingBottom
        )
    }.apply {
      text = typeArray.getString(R.styleable.ShimmerRibbonView_shimmer_ribbon_text)
      setTextColor(
        typeArray.getColor(R.styleable.ShimmerRibbonView_shimmer_ribbon_textColor, Color.WHITE)
      )
      textSize =
        typeArray.getDimensionPixelSize(R.styleable.ShimmerRibbonView_shimmer_ribbon_textSize, 12)
          .toFloat()

      when (typeArray.getInteger(R.styleable.ShimmerRibbonView_shimmer_ribbon_textStyle, 0)) {
        0 -> typeface = Typeface.DEFAULT_BOLD
        1 -> setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC), Typeface.ITALIC)
      }
    }
    frameRotation =
      typeArray.getInt(R.styleable.ShimmerRibbonView_shimmer_ribbon_rotation, frameRotation)
  }

  private fun onCreateByBuilder(builder: Builder) {
    ribbon = builder.ribbon
    shimmer = builder.shimmer
    updateRibbon()
  }

  /** update [ShimmerRibbonView] after finishing inflate. */
  override fun onFinishInflate() {
    super.onFinishInflate()
    updateRibbon()
  }

  /** update [ShimmerRibbonView] by attributes. */
  override fun updateRibbon() {
    removeAllViews()
    addView(ribbon)
    invalidate()
  }

  override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    super.onLayout(changed, left, top, right, bottom)
    if (changed) {
      rotation(frameRotation)
    }
  }

  /** [ShimmerRibbonView] builder class. */
  class Builder(val context: Context) {

    @JvmField
    var ribbon = RibbonView(context)

    @JvmField
    var shimmer: Shimmer =
      Shimmer
        .AlphaHighlightBuilder()
        .setBaseAlpha(1.0f)
        .setHighlightAlpha(0.5f)
        .build()

    /** sets [RibbonView] on builder. */
    fun setRibbonView(ribbonView: RibbonView) = apply { this.ribbon = ribbonView }

    /** sets [Shimmer] on builder. */
    fun setShimmer(shimmer: Shimmer) = apply { this.shimmer = shimmer }

    /** assembles builder's attributes and returns [ShimmerRibbonView]. */
    fun build(): ShimmerRibbonView {
      return ShimmerRibbonView(context).apply {
        onCreateByBuilder(this@Builder)
      }
    }
  }
}
