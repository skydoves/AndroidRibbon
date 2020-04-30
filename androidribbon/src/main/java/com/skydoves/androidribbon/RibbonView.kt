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
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.skydoves.androidribbon.annotations.Dp
import com.skydoves.androidribbon.annotations.Sp

@DslMarker
annotation class RibbonDsl

/** create a [RibbonView] by [RibbonView.Builder] using dsl. */
@Suppress("unused")
fun ribbonView(context: Context, block: RibbonView.Builder.() -> Unit): RibbonView =
  RibbonView.Builder(context).apply(block).build()

/** RibbonView is advanced of [AppCompatTextView] for implement ribbon. */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class RibbonView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = android.R.attr.textViewStyle
) : AppCompatTextView(context, attrs, defStyleAttr), RibbonInterface {

  var ribbonDrawable: Drawable? = null
    set(value) {
      field = value
      updateRibbon()
    }
  @ColorInt
  var ribbonBackgroundColor = ContextCompat.getColor(context, R.color.colorPrimary)
    set(value) {
      field = value
      background.apply {
        if (this is GradientDrawable) {
          this.setColor(value)
        }
      }
    }
  var ribbonRotation = 0
    set(value) {
      field = value
      rotation(value)
    }
  @Dp var ribbonRadius = 10f
    set(value) {
      field = value
      updateRibbon()
    }
  @Dp var paddingLeft = 8f
    set(value) {
      field = value
      updateRibbon()
    }
  @Dp var paddingTop = 4f
    set(value) {
      field = value
      updateRibbon()
    }
  @Dp var paddingRight = 8f
    set(value) {
      field = value
      updateRibbon()
    }
  @Dp var paddingBottom = 4f
    set(value) {
      field = value
      updateRibbon()
    }

  init {
    onCreate()
    when {
      attrs != null && defStyleAttr != android.R.attr.textViewStyle ->
        getAttrs(attrs, defStyleAttr)
      attrs != null -> getAttrs(attrs)
    }
  }

  private fun onCreate() {
    this.gravity = Gravity.CENTER
    this.background = GradientDrawable().apply {
      setColor(ribbonBackgroundColor)
      cornerRadius = ribbonRadius
    }
  }

  private fun getAttrs(attributeSet: AttributeSet) {
    val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.RibbonView)
    try {
      setTypeArray(typedArray)
    } finally {
      typedArray.recycle()
    }
  }

  private fun getAttrs(attributeSet: AttributeSet, defStyleAttr: Int) {
    val typedArray =
      context.obtainStyledAttributes(attributeSet, R.styleable.RibbonView, defStyleAttr, 0)
    try {
      setTypeArray(typedArray)
    } finally {
      typedArray.recycle()
    }
  }

  private fun setTypeArray(typeArray: TypedArray) {
    this.ribbonDrawable = typeArray.getDrawable(R.styleable.RibbonView_ribbon_drawable)
    this.ribbonBackgroundColor =
      typeArray.getColor(R.styleable.RibbonView_ribbon_background_color, ribbonBackgroundColor)
    this.ribbonRadius =
      typeArray.getDimension(R.styleable.RibbonView_ribbon_ribbonRadius, ribbonRadius)
    this.ribbonRotation = typeArray.getInt(R.styleable.RibbonView_ribbon_rotation, ribbonRotation)
    this.paddingLeft =
      typeArray.getDimension(R.styleable.RibbonView_ribbon_padding_left, paddingLeft)
    this.paddingTop = typeArray.getDimension(R.styleable.RibbonView_ribbon_padding_top, paddingTop)
    this.paddingRight =
      typeArray.getDimension(R.styleable.RibbonView_ribbon_padding_right, paddingRight)
    this.paddingBottom =
      typeArray.getDimension(R.styleable.RibbonView_ribbon_padding_bottom, paddingBottom)
  }

  /** initialize attributes by [RibbonView.Builder] */
  private fun onCreateByBuilder(builder: Builder) {
    this.ribbonDrawable = builder.ribbonDrawable
    this.ribbonBackgroundColor = builder.ribbonBackgroundColor
    this.ribbonRadius = builder.ribbonRadius
    this.ribbonRotation = builder.ribbonRotation
    this.paddingLeft = builder.paddingLeft
    this.paddingTop = builder.paddingTop
    this.paddingRight = builder.paddingRight
    this.paddingBottom = builder.paddingBottom
    this.text = builder.text
    setTextColor(builder.textColor)
    this.textSize = builder.textSize
    setTypeface(typeface, builder.textStyle)

    updateRibbon()
  }

  /** update [RibbonView] after finishing inflate. */
  override fun onFinishInflate() {
    super.onFinishInflate()
    updateRibbon()
  }

  /** update [RibbonView] by attributes. */
  override fun updateRibbon() {
    setPadding(
      paddingLeft.dp2px(resources),
      paddingTop.dp2px(resources),
      paddingRight.dp2px(resources),
      paddingBottom.dp2px(resources)
    )

    this.ribbonDrawable?.let {
      background = ribbonDrawable
    } ?: let {
      if (background is GradientDrawable) {
        val bgShape = background as GradientDrawable
        bgShape.cornerRadius = ribbonRadius.dp2px(resources).toFloat()
        bgShape.setColor(ribbonBackgroundColor)
      }
    }
  }

  override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    super.onLayout(changed, left, top, right, bottom)
    rotation(ribbonRotation)
  }

  /** Builder class for creating [RibbonView]. */
  @RibbonDsl
  class Builder(val context: Context) {
    @JvmField
    var ribbonDrawable: Drawable? = null
    @JvmField @ColorInt
    var ribbonBackgroundColor = ContextCompat.getColor(context, R.color.colorPrimary)
    @JvmField
    var ribbonRotation = 0
    @JvmField @Dp
    var ribbonRadius = 10f
    @JvmField @Dp
    var paddingLeft = 8f
    @JvmField @Dp
    var paddingTop = 4f
    @JvmField @Dp
    var paddingRight = 8f
    @JvmField @Dp
    var paddingBottom = 4f
    @JvmField
    var text = ""
    @JvmField @ColorInt
    var textColor = Color.WHITE
    @JvmField @Sp
    var textSize = 12f
    @JvmField
    var textStyle = Typeface.NORMAL

    fun setRibbonDrawable(RibbonDrawable: Drawable?): Builder = apply {
      this.ribbonDrawable = RibbonDrawable
    }

    fun setRibbonDrawableResource(@DrawableRes drawable: Int): Builder = apply {
      this.ribbonDrawable = ContextCompat.getDrawable(this.context, drawable)
    }

    fun setRibbonBackgroundColor(@ColorInt color: Int): Builder = apply {
      this.ribbonBackgroundColor = color
    }

    fun setRibbonBackgroundColorResource(@ColorRes color: Int): Builder = apply {
      this.ribbonBackgroundColor = ContextCompat.getColor(this.context, color)
    }

    fun setRibbonRotation(value: Int): Builder = apply { this.ribbonRotation = value }
    fun setRibbonRadius(@Dp value: Float): Builder = apply { this.ribbonRadius = value }
    fun setPaddingLeft(@Dp value: Float): Builder = apply { this.paddingLeft = value }
    fun setPaddingTop(@Dp value: Float): Builder = apply { this.paddingTop = value }
    fun setPaddingRight(@Dp value: Float): Builder = apply { this.paddingRight = value }
    fun setPaddingBottom(@Dp value: Float): Builder = apply { this.paddingBottom = value }
    fun setText(value: String): Builder = apply { this.text = value }
    fun setTextColor(@ColorInt value: Int): Builder = apply { this.textColor = value }
    fun setTextSize(@Sp value: Float): Builder = apply { this.textSize = value }
    fun setTextStyle(value: Int): Builder = apply { this.textStyle = value }
    fun build(): RibbonView = RibbonView(context).apply { onCreateByBuilder(this@Builder) }
  }
}
